package net.romvoid95.common.world.cave.event;

import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.common.world.cave.MapGenBetterCaves;
import net.romvoid95.common.world.cave.mineshaft.MapGenBetterMineshaft;
import net.romvoid95.common.world.cave.ravine.MapGenBetterRavine;

/**
 * Replaces vanilla cave generation with Better Caves generation.
 * Should be registered to the {@code TERRAIN_GEN_BUS}.
 */
public class EventBetterCaveGen {
    /**
     * Replaces cave gen and mineshaft gen
     *
     * @param event Map generation event
     */
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onInitMapGenEvent(InitMapGenEvent event) {
        // Replace cave gen with Better Caves
        if (
            (event.getType() == InitMapGenEvent.EventType.CAVE || event.getType() == InitMapGenEvent.EventType.NETHER_CAVE)
                && !event.getOriginalGen().getClass().equals(MapGenBetterCaves.class)
        ) {
            event.setNewGen(new MapGenBetterCaves(event));
        }
        // Replace mineshaft gen with Better Caves
        else if (
            event.getType() == InitMapGenEvent.EventType.MINESHAFT
                && event.getOriginalGen() == event.getNewGen() // only modify vanilla gen to allow other mods to modify mineshafts
        ) {
            event.setNewGen(new MapGenBetterMineshaft(event));
        }
        // Replace ravine gen with Better Caves
        else if (
            event.getType() == InitMapGenEvent.EventType.RAVINE
                && event.getOriginalGen() == event.getNewGen() // only modify vanilla gen to allow other mods to modify ravines
        ) {
            event.setNewGen(new MapGenBetterRavine(event));
        }
    }
}
