package net.rom.exoplanets.netlib;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.rom.exoplanets.ExoplanetsMod;

public class PacketHandle extends SimpleChannelInboundHandler<IExoMessage> {

	@Override
	protected void channelRead0 (ChannelHandlerContext ctx, IExoMessage msg) throws Exception {
		INetHandler  netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
		EntityPlayer player     = ExoplanetsMod.proxy.getPlayerFromNetHandler(netHandler);

		switch (FMLCommonHandler.instance().getEffectiveSide()) {
		case CLIENT:
			msg.handleClientSide(player);
			break;
		case SERVER:
			msg.handleServerSide(player);
			break;
		default:
			break;
		}
	}
}
