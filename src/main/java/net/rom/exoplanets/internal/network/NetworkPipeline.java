/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal.network;

import io.netty.channel.ChannelHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.rom.exoplanets.internal.network.packet.AbstractClientPacketHandler;

@ChannelHandler.Sharable
public class NetworkPipeline {
    public final SimpleNetworkWrapper dispatcher;
    protected int packetID;

    public NetworkPipeline() {
        dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel("exoplanets");
        packetID = 0;
    }
    
    public void registerPackets() {
    }
    
    public <REQ extends IMessage, REPLY extends IMessage> void registerPacket(Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> reresearchMessageType) {
        try {
            Side side = AbstractClientPacketHandler.class.isAssignableFrom(messageHandler) ? Side.CLIENT : Side.SERVER;
            dispatcher.registerMessage(messageHandler, reresearchMessageType, packetID++, side);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToServer(IMessage message) {
        dispatcher.sendToServer(message);
    }

    public void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) {
        dispatcher.sendToAllAround(message, point);
    }

    public void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) {
        dispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
    }

    public void sendToAllAround(IMessage message, EntityPlayer player, double range) {
        dispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(player.world.provider.getDimension(), player.posX, player.posY, player.posZ, range));
    }

    public void sendToAllAround(IMessage message, TileEntity tileEntity, double range) {
        dispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(tileEntity.getWorld().provider.getDimension(), tileEntity.getPos().getX(), tileEntity.getPos().getY(), tileEntity.getPos().getZ(), range));
    }

    public void sendTo(IMessage message, EntityPlayerMP player) {
        dispatcher.sendTo(message, player);
    }

    public void sendToDimention(IMessage message, int dimention) {
        dispatcher.sendToDimension(message, dimention);
    }

    public void sendToDimention(IMessage message, World world) {
        sendToDimention(message, world.provider);
    }

    public void sendToDimention(IMessage message, WorldProvider worldProvider) {
        dispatcher.sendToDimension(message, worldProvider.getDimension());
    }
}
