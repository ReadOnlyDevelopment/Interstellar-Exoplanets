package net.rom.exoplanets.netlib;

import io.netty.buffer.ByteBuf;

public abstract class NetPacket implements IExoMessage {
	public static final int INVALID_DIMENSION_ID = Integer.MIN_VALUE + 12;
	private int             dimensionID;

	public NetPacket() {
		this.dimensionID = INVALID_DIMENSION_ID;
	}

	public NetPacket(int dimensionID) {
		this.dimensionID = dimensionID;
	}

	@Override
	public void encodeInto (ByteBuf buffer) {
		if (dimensionID == INVALID_DIMENSION_ID) {
			throw new IllegalStateException("Invalid ID!");
		}
		buffer.writeInt(this.dimensionID);
	}

	@Override
	public void decodeInto (ByteBuf buffer) {
		this.dimensionID = buffer.readInt();
	}

	@Override
	public int getDimensionID () {
		return dimensionID;
	}

}
