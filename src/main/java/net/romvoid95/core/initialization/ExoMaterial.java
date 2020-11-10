package net.romvoid95.core.initialization;

import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class ExoMaterial extends Material {
	
		public static final ExoMaterial METAL = (new ExoMaterial(MapColor.GRAY_STAINED_HARDENED_CLAY)).setRequiresTool();
		public static final ExoMaterial SLUDGE = (new ExoMaterial(MapColor.BROWN));
		public static final ExoMaterial BIO = (new ExoMaterial(MapColor.GREEN));

		private boolean				isTranslucent;
		private final MapColor		materialMapColor;
		private boolean				requiresNoTool	= true;
		private EnumPushReaction	mobilityFlag	= EnumPushReaction.NORMAL;
		private boolean				isLiquid		= false;
		private boolean				isSolid			= true;
		private boolean				blocksLight		= true;
		private boolean				blocksMovement	= true;

		public ExoMaterial (MapColor color) {
			super(color);
			this.materialMapColor = color;
		}

		/**
		 * Returns if blocks of these materials are liquids.
		 */
		@Override
		public boolean isLiquid() {
			return this.isLiquid;
		}

		/**
		 * Returns true if the block is a considered solid. This is true by default.
		 */
		@Override
		public boolean isSolid() {
			return this.isSolid;
		}

		/**
		 * Will prevent grass from growing on dirt underneath and kill any grass below it if it returns true
		 */
		@Override
		public boolean blocksLight() {
			return this.blocksLight;
		}

		/**
		 * Returns if this material is considered solid or not
		 */
		@Override
		public boolean blocksMovement() {
			return this.blocksMovement;
		}

		@Override
		public boolean isToolNotRequired() {
			return this.requiresNoTool;
		}

		@Override
		public EnumPushReaction getMobilityFlag() {
			return this.mobilityFlag;
		}

		@Override
		protected ExoMaterial setRequiresTool() {
			this.requiresNoTool = false;
			return this;
		}

		@Override
		protected ExoMaterial setNoPushMobility() {
			this.mobilityFlag = EnumPushReaction.DESTROY;
			return this;
		}

		protected ExoMaterial asLiquid() {
			this.isLiquid = true;
			this.isSolid = false;
			this.blocksMovement = false;
			return this;
		}

		private ExoMaterial setTranslucent() {
			this.isTranslucent = true;
			this.blocksLight = false;
			return this;
		}

		@Override
		public boolean isOpaque() {
			return this.isTranslucent ? false : this.blocksMovement();
		}

		@Override
		public MapColor getMaterialMapColor() {
			return this.materialMapColor;
		}
}
