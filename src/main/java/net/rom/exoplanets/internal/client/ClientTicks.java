package net.rom.exoplanets.internal.client;

import java.util.ArrayDeque;
import java.util.Queue;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.util.LogHelper;
import net.rom.exoplanets.util.MCUtil;

@Mod.EventBusSubscriber(modid = ExoInfo.MODID, value = Side.CLIENT)
public class ClientTicks {
    @Deprecated
    public static final ClientTicks INSTANCE = new ClientTicks();

    private static final int QUEUE_OVERFLOW_LIMIT = 200;

    private static volatile Queue<Runnable> scheduledActions = new ArrayDeque<>();

    public static int ticksInGame = 0;
    public static float partialTicks = 0f;
    public static float deltaTicks = 0f;
    public static float totalTicks = 0f;

    private ClientTicks() {}

    public static void scheduleAction(Runnable action) {
        if (MCUtil.isClient())
            scheduledActions.add(action);
        else
        	LogHelper.formatted_Error("Tried to add client tick action on server side? {}", action);

        if (scheduledActions.size() >= QUEUE_OVERFLOW_LIMIT) {
            // Queue overflow?
        	LogHelper.formatted_Warn("Too many client tick actions queued! Currently at {} items. Would have added '{}'.",
                    scheduledActions.size(), action);
        	LogHelper.catching(new IllegalStateException("ClientTicks queue overflow"));
            scheduledActions.clear();
        }
    }

    @SubscribeEvent
    public static void clientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        runScheduledActions();
        updateTickCounters();
    }

    @SubscribeEvent
    public static void renderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START)
            partialTicks = event.renderTickTime;
    }

    private static void runScheduledActions() {
        Runnable action = scheduledActions.poll();
        while (action != null) {
            action.run();
            action = scheduledActions.poll();
        }
    }

    private static void updateTickCounters() {
        GuiScreen gui = Minecraft.getMinecraft().currentScreen;
        if (gui == null || !gui.doesGuiPauseGame()) {
            ++ticksInGame;
            partialTicks = 0;
        }

        float oldTotal = totalTicks;
        totalTicks = ticksInGame + partialTicks;
        deltaTicks = totalTicks - oldTotal;
    }
}
