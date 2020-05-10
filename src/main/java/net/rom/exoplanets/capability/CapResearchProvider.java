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

package net.rom.exoplanets.capability;

import java.lang.ref.WeakReference;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapResearchProvider implements ICapabilitySerializable<NBTTagCompound> {

	private EntityPlayerMP player;
	private IResearchCapability researchCap;

	public CapResearchProvider(EntityPlayerMP player) {
		this.player = player;
		this.researchCap = CapResearchHandler.RESEARCH_CAPABILITY.getDefaultInstance();
		this.researchCap.setPlayer(this.player);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub

	}

}
