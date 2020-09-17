package net.romvoid95.common.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.romvoid95.client.ClientUtil;

public interface IExoNetworkHandler<REQ extends IMessage, REPLY extends IMessage> extends IMessageHandler<REQ, REPLY> {
	@Override
	public default REPLY onMessage (REQ message, MessageContext ctx) {
		if (useTask()) {
			if (ctx.side.isClient())
				Minecraft.getMinecraft().addScheduledTask( () -> process(message, ctx));
			else if (ctx.side.isServer())
				((WorldServer) ctx.getServerHandler().player.world).addScheduledTask( () -> process(message, ctx));
		}
		else {
			process(message, ctx);
		}
		return null;
	}

	/**
	 * Processes the received message. This is executed on the main thread.
	 *
	 * @param message the message
	 * @param ctx the ctx
	 */
	public void process (REQ message, MessageContext ctx);

	public default boolean useTask () {
		return true;
	}

	/**
	 * Gets the correct client or server {@link World} based on {@link MessageContext}.
	 *
	 * @param ctx the ctx
	 * @return the world
	 */
	public static World getWorld (MessageContext ctx) {
		if (ctx.side == Side.SERVER)
			return ctx.getServerHandler().player.world;
		else
			return ClientUtil.getClientWorld();
	}

	/**
	 * Gets the correct client or server {@link EntityPlayer} based on {@link MessageContext}.
	 *
	 * @param ctx the ctx
	 * @return the player
	 */
	public static EntityPlayer getPlayer (MessageContext ctx) {
		if (ctx.side == Side.SERVER)
			return ctx.getServerHandler().player;
		else
			return ClientUtil.getClientPlayer();
	}
}