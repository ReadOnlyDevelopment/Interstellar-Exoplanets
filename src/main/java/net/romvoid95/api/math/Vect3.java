package net.romvoid95.api.math;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.client.RGB;

public class Vect3 extends Vector3 {

	public double x;
	public double y;
	public double z;

	public Vect3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.y = y;
	}

	public Vect3(Vector3 vector3) {
		new Vect3(vector3.x, vector3.y, vector3.z);
	}

	public Vect3() {
		this(0, 0, 0);
	}

	public Vect3(Vect3 vector) {
		this(vector.x, vector.y, vector.z);
	}

	public Vect3(double amount) {
		this(amount, amount, amount);
	}

	public Vect3(Entity par1) {
		this(par1.posX, par1.posY, par1.posZ);
	}

	public Vect3(BlockPos pos) {
		this(pos.getX(), pos.getY(), pos.getZ());
	}

	public Vect3(TileEntity par1) {
		this(par1.getPos());
	}

	@SideOnly(Side.CLIENT)
	public BufferBuilder toPos (BufferBuilder b, Vect3 v, RGB c) {
		return b.pos(v.x, v.y, v.z).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
	}

}
