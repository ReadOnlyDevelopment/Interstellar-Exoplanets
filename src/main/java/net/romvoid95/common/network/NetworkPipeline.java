/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.romvoid95.common.network;

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
import net.romvoid95.common.network.packet.AbstractClientPacketHandler;

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
