package net.rom.exoplanets.netlib;

import java.util.EnumMap;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLIndexedMessageToMessageCodec;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.rom.exoplanets.ExoInfo;

public class ExoplanetsChannelHandler extends FMLIndexedMessageToMessageCodec<IExoMessage> {
	private EnumMap<Side, FMLEmbeddedChannel> channels;

	private ExoplanetsChannelHandler() {
		this.addDiscriminator(0, SimplePacket.class);
	}

	public static ExoplanetsChannelHandler init () {
		ExoplanetsChannelHandler channelHandler = new ExoplanetsChannelHandler();
		channelHandler.channels = NetworkRegistry.INSTANCE
				.newChannel(ExoInfo.MODID, channelHandler, new ExoplanetsChannelHandler());
		return channelHandler;
	}

	@Override
	public void encodeInto (ChannelHandlerContext ctx, IExoMessage msg, ByteBuf target) throws Exception {
		msg.encodeInto(target);
	}

	@Override
	public void decodeInto (ChannelHandlerContext ctx, ByteBuf source, IExoMessage msg) {
		msg.decodeInto(source);
	}

	/**
	 * Send this message to everyone.
	 * <p/>
	 * Adapted from CPW's code in
	 * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message The message to send
	 */
	public void sendToAll (IExoMessage message) {
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
				.set(FMLOutboundHandler.OutboundTarget.ALL);
		this.channels.get(Side.SERVER).writeOutbound(message);
	}

	/**
	 * Send this message to the specified player.
	 * <p/>
	 * Adapted from CPW's code in
	 * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message The message to send
	 * @param player  The player to send it to
	 */
	public void sendTo (IExoMessage message, EntityPlayerMP player) {
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
				.set(FMLOutboundHandler.OutboundTarget.PLAYER);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		this.channels.get(Side.SERVER).writeOutbound(message);
	}

	/**
	 * Send this message to everyone within a certain range of a point.
	 * <p/>
	 * Adapted from CPW's code in
	 * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message The message to send
	 * @param point   The
	 *                {@link cpw.mods.fml.common.network.NetworkRegistry.TargetPoint}
	 *                around which to send
	 */
	public void sendToAllAround (IExoMessage message, NetworkRegistry.TargetPoint point) {
		try {
			this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
					.set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
			this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
			this.channels.get(Side.SERVER).writeOutbound(message);
		}
		catch (Exception e) {
			System.out
					.println("Forge error when sending network packet to all players in dimension - this is not a Exoplanets bug");
			e.printStackTrace();
		}
	}

	/**
	 * Send this message to everyone within the supplied dimension.
	 * <p/>
	 * Adapted from CPW's code in
	 * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message     The message to send
	 * @param dimensionId The dimension id to target
	 */
	public void sendToDimension (IExoMessage message, int dimensionId) {
		try {
			this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
					.set(FMLOutboundHandler.OutboundTarget.DIMENSION);
			this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
			this.channels.get(Side.SERVER).writeOutbound(message);
		}
		catch (Exception e) {
			System.out
					.println("Forge error when sending network packet to all players in dimension - this is not a Exoplanets bug");
			e.printStackTrace();
		}
	}

	/**
	 * Send this message to the server.
	 * <p/>
	 * Adapted from CPW's code in
	 * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
	 *
	 * @param message The message to send
	 */
	public void sendToServer (IExoMessage message) {
		if (FMLCommonHandler.instance().getSide() != Side.CLIENT)
			return;
		this.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET)
				.set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		this.channels.get(Side.CLIENT).writeOutbound(message);
	}
}
