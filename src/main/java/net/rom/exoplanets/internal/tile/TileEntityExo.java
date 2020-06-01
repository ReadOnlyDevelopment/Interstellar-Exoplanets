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

package net.rom.exoplanets.internal.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityExo extends TileEntity {
    public final void sendUpdate() {
        if (world != null && !world.isRemote) {
            IBlockState state = world.getBlockState(pos);
            world.notifyBlockUpdate(pos, state, state, 3);
            markDirty();
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound tags) {
        super.readFromNBT(tags);
        readSyncVars(tags);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tags) {
        super.writeToNBT(tags);
        writeSyncVars(tags, SyncVariable.Type.WRITE);
        return tags;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tags = getUpdateTag();
        return new SPacketUpdateTileEntity(pos, 1, tags);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        NBTTagCompound tags = super.getUpdateTag();
        writeSyncVars(tags, SyncVariable.Type.PACKET);
        return tags;
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        readSyncVars(packet.getNbtCompound());
    }

    protected void readSyncVars(NBTTagCompound tags) {
        SyncVariable.Helper.readSyncVars(this, tags);
    }

    protected NBTTagCompound writeSyncVars(NBTTagCompound tags, SyncVariable.Type syncType) {
        return SyncVariable.Helper.writeSyncVars(this, tags, syncType);
    }
}
