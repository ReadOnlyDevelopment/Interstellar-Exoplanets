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
package net.rom.exoplanets.world;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.planets.asteroids.blocks.AsteroidBlocks;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockDoor.EnumHingePosition;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces.PieceWeight;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;
import net.minecraft.world.gen.structure.StructureVillagePieces.Village;
import net.minecraftforge.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;

public class VillageAstronomerHouse extends Village { // NO_UCD (use default)
	public VillageAstronomerHouse() {}

	public VillageAstronomerHouse(Start villagePiece, int par2, Random par3Random,
			StructureBoundingBox par4StructureBoundingBox, EnumFacing facing) { // NO_UCD (use private)
		super(villagePiece, par2);
		this.setCoordBaseMode(facing);
		this.boundingBox = par4StructureBoundingBox;
	}

	private int groundLevel = -1;

	@Override
	public boolean addComponentParts (World world, Random rand, StructureBoundingBox box) {
		if (groundLevel < 0) {
			groundLevel = this.getAverageGroundLevel(world, box);
			if (groundLevel < 0)
				return true;
			boundingBox.offset(0, groundLevel - boundingBox.maxY + 10 - 1, 0);
		}

		// Clear Space
		this.fillWithBlocks(world, box, 0, 0, 0, 10, 9, 8, Blocks.AIR.getDefaultState(), Blocks.AIR
				.getDefaultState(), false);
		// Cobble
		this.fillWithBlocks(world, box, 1, 0, 1, 9, 0, 8, Blocks.COBBLESTONE.getDefaultState(), Blocks.COBBLESTONE
				.getDefaultState(), false);
		this.fillWithBlocks(world, box, 6, 0, 1, 9, 0, 2, Blocks.AIR.getDefaultState(), Blocks.AIR
				.getDefaultState(), false);
		// Stair
		this.setBlockState(world, Blocks.STONE_STAIRS.getDefaultState()
				.withProperty(BlockStairs.FACING, EnumFacing.NORTH), 4, 0, 0, box);

		// Pillars
		this.fillWithBlocks(world, box, 1, 1, 3, 1, 4, 3, GCBlocks.basicBlock.getStateFromMeta(3), GCBlocks.basicBlock
				.getStateFromMeta(3), false);
		this.fillWithBlocks(world, box, 1, 1, 8, 1, 6, 8, GCBlocks.basicBlock.getStateFromMeta(3), GCBlocks.basicBlock
				.getStateFromMeta(3), false);
		this.fillWithBlocks(world, box, 9, 1, 3, 9, 6, 3, GCBlocks.basicBlock.getStateFromMeta(3), GCBlocks.basicBlock
				.getStateFromMeta(3), false);
		this.fillWithBlocks(world, box, 9, 1, 8, 9, 6, 8, GCBlocks.basicBlock.getStateFromMeta(3), GCBlocks.basicBlock
				.getStateFromMeta(3), false);
		this.fillWithBlocks(world, box, 1, 4, 3, 9, 4, 8, GCBlocks.basicBlock.getStateFromMeta(3), GCBlocks.basicBlock
				.getStateFromMeta(3), false);
		this.fillWithBlocks(world, box, 6, 5, 3, 6, 7, 3, GCBlocks.basicBlock.getStateFromMeta(3), GCBlocks.basicBlock
				.getStateFromMeta(3), false);
		this.fillWithBlocks(world, box, 1, 5, 5, 1, 6, 5, GCBlocks.basicBlock.getStateFromMeta(3), GCBlocks.basicBlock
				.getStateFromMeta(3), false);

		this.fillWithBlocks(world, box, 2, 4, 5, 8, 4, 7, Blocks.AIR.getDefaultState(), Blocks.AIR
				.getDefaultState(), false);

		// Wool
		this.fillWithBlocks(world, box, 2, 0, 3, 5, 0, 4, AsteroidBlocks.blockBasic
				.getStateFromMeta(6), AsteroidBlocks.blockBasic.getStateFromMeta(6), false);
		this.fillWithBlocks(world, box, 2, 0, 4, 8, 0, 7, AsteroidBlocks.blockBasic
				.getStateFromMeta(6), AsteroidBlocks.blockBasic.getStateFromMeta(6), false);
		this.fillWithBlocks(world, box, 6, 4, 4, 8, 4, 4, AsteroidBlocks.blockBasic
				.getStateFromMeta(6), AsteroidBlocks.blockBasic.getStateFromMeta(6), false);
		this.fillWithBlocks(world, box, 2, 4, 5, 7, 4, 5, AsteroidBlocks.blockBasic
				.getStateFromMeta(6), AsteroidBlocks.blockBasic.getStateFromMeta(6), false);
		this.fillWithBlocks(world, box, 2, 4, 6, 6, 4, 6, AsteroidBlocks.blockBasic
				.getStateFromMeta(6), AsteroidBlocks.blockBasic.getStateFromMeta(6), false);
		this.fillWithBlocks(world, box, 2, 4, 7, 4, 4, 7, AsteroidBlocks.blockBasic
				.getStateFromMeta(6), AsteroidBlocks.blockBasic.getStateFromMeta(6), false);

		// Walls
		// Front
		this.fillWithBlocks(world, box, 2, 1, 3, 8, 3, 3, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.fillWithBlocks(world, box, 7, 5, 3, 8, 6, 3, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.setBlockState(world, GCBlocks.basicBlock.getStateFromMeta(4), 7, 7, 3, box);
		this.fillWithBlocks(world, box, 6, 5, 4, 6, 7, 4, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.fillWithBlocks(world, box, 2, 5, 5, 5, 6, 5, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.fillWithBlocks(world, box, 3, 7, 5, 5, 7, 5, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.setBlockState(world, GCBlocks.basicBlock.getStateFromMeta(4), 5, 8, 5, box);
		// Back
		this.fillWithBlocks(world, box, 2, 1, 8, 8, 3, 8, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.fillWithBlocks(world, box, 2, 5, 8, 8, 6, 8, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.fillWithBlocks(world, box, 3, 7, 8, 7, 7, 8, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.setBlockState(world, GCBlocks.basicBlock.getStateFromMeta(4), 5, 8, 8, box);
		// Left
		this.fillWithBlocks(world, box, 1, 1, 4, 1, 3, 7, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.fillWithBlocks(world, box, 1, 5, 6, 1, 5, 7, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		// Right
		this.fillWithBlocks(world, box, 9, 1, 4, 9, 3, 7, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.fillWithBlocks(world, box, 9, 5, 4, 9, 6, 7, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);

		// Windows
		// Front
		this.setBlockState(world, GCBlocks.spaceGlassClear.getDefaultState(), 2, 2, 3, box);
		this.setBlockState(world, GCBlocks.spaceGlassClear.getDefaultState(), 6, 2, 3, box);
		this.setBlockState(world, GCBlocks.spaceGlassClear.getDefaultState(), 8, 2, 3, box);
		this.fillWithBlocks(world, box, 7, 6, 3, 8, 6, 3, GCBlocks.spaceGlassClear
				.getDefaultState(), GCBlocks.spaceGlassClear.getDefaultState(), false);
		// Back
		this.fillWithBlocks(world, box, 3, 2, 8, 5, 2, 8, GCBlocks.spaceGlassClear
				.getDefaultState(), GCBlocks.spaceGlassClear.getDefaultState(), false);
		this.fillWithBlocks(world, box, 3, 6, 8, 4, 6, 8, GCBlocks.spaceGlassClear
				.getDefaultState(), GCBlocks.spaceGlassClear.getDefaultState(), false);
		this.fillWithBlocks(world, box, 6, 6, 8, 7, 6, 8, GCBlocks.spaceGlassClear
				.getDefaultState(), GCBlocks.spaceGlassClear.getDefaultState(), false);
		// Left
		this.fillWithBlocks(world, box, 1, 2, 5, 1, 2, 6, GCBlocks.spaceGlassClear
				.getDefaultState(), GCBlocks.spaceGlassClear.getDefaultState(), false);
		this.fillWithBlocks(world, box, 1, 6, 6, 1, 6, 7, GCBlocks.spaceGlassClear
				.getDefaultState(), GCBlocks.spaceGlassClear.getDefaultState(), false);
		// Right
		this.fillWithBlocks(world, box, 9, 2, 5, 9, 2, 6, GCBlocks.spaceGlassClear
				.getDefaultState(), GCBlocks.spaceGlassClear.getDefaultState(), false);
		this.fillWithBlocks(world, box, 9, 6, 5, 9, 6, 6, GCBlocks.spaceGlassClear
				.getDefaultState(), GCBlocks.spaceGlassClear.getDefaultState(), false);

		// Fences
		//		this.fillWithBlocks(world, box, 1, 1, 1, 1, 1, 2, IEContent.blockWoodenDecoration.getStateFromMeta(0), IEContent.blockWoodenDecoration.getStateFromMeta(0), false);
		//		this.fillWithBlocks(world, box, 2, 1, 1, 3, 1, 1, IEContent.blockWoodenDecoration.getStateFromMeta(0), IEContent.blockWoodenDecoration.getStateFromMeta(0), false);
		//		this.fillWithBlocks(world, box, 5, 1, 1, 5, 1, 2, IEContent.blockWoodenDecoration.getStateFromMeta(0), IEContent.blockWoodenDecoration.getStateFromMeta(0), false);
		//		this.fillWithBlocks(world, box, 1, 5, 3, 1, 5, 4, IEContent.blockWoodenDecoration.getStateFromMeta(0), IEContent.blockWoodenDecoration.getStateFromMeta(0), false);
		//		this.fillWithBlocks(world, box, 2, 5, 3, 5, 5, 3, IEContent.blockWoodenDecoration.getStateFromMeta(0), IEContent.blockWoodenDecoration.getStateFromMeta(0), false);
		//		this.fillWithBlocks(world, box, 7, 1, 6, 7, 5, 6, IEContent.blockWoodenDecoration.getStateFromMeta(0), IEContent.blockWoodenDecoration.getStateFromMeta(0), false);

		// Stairs
		IBlockState stairs = GCBlocks.tinStairs2.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
		setBlockState(world, stairs, 8, 1, 6, box);
		stairs = stairs.withRotation(Rotation.COUNTERCLOCKWISE_90);
		setBlockState(world, GCBlocks.basicBlock.getStateFromMeta(4), 8, 1, 7, box);
		setBlockState(world, stairs, 7, 2, 7, box);
		setBlockState(world, stairs, 6, 3, 7, box);
		setBlockState(world, stairs, 5, 4, 7, box);

		// Roof
		IBlockState brickSlab         = GCBlocks.slabGCHalf.getStateFromMeta(1);
		IBlockState brickSlabInverted = brickSlab.withProperty(BlockSlab.HALF, EnumBlockHalf.TOP);
		this.fillWithBlocks(world, box, 0, 6, 4, 0, 6, 8, brickSlabInverted, brickSlabInverted, false);
		this.fillWithBlocks(world, box, 1, 7, 4, 1, 7, 8, brickSlab, brickSlab, false);
		this.fillWithBlocks(world, box, 3, 8, 4, 3, 8, 8, brickSlab, brickSlab, false);
		this.fillWithBlocks(world, box, 5, 9, 2, 5, 9, 8, brickSlab, brickSlab, false);
		this.fillWithBlocks(world, box, 7, 8, 2, 7, 8, 8, brickSlab, brickSlab, false);
		this.fillWithBlocks(world, box, 9, 7, 2, 9, 7, 8, brickSlab, brickSlab, false);
		this.fillWithBlocks(world, box, 10, 6, 2, 10, 6, 8, brickSlabInverted, brickSlabInverted, false);

		IBlockState brickStairs = GCBlocks.tinStairs2.getDefaultState()
				.withProperty(BlockStairs.FACING, EnumFacing.WEST);
		this.fillWithBlocks(world, box, 2, 7, 4, 2, 7, 8, brickStairs, brickStairs, false);
		this.fillWithBlocks(world, box, 4, 8, 4, 4, 8, 8, brickStairs, brickStairs, false);
		brickStairs = brickStairs.withRotation(Rotation.CLOCKWISE_90);
		this.fillWithBlocks(world, box, 6, 8, 2, 6, 8, 8, brickStairs, brickStairs, false);
		this.fillWithBlocks(world, box, 8, 7, 2, 8, 7, 8, brickStairs, brickStairs, false);

		this.fillWithBlocks(world, box, 2, 7, 5, 2, 8, 5, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);
		this.fillWithBlocks(world, box, 7, 8, 4, 7, 9, 4, GCBlocks.basicBlock.getStateFromMeta(4), GCBlocks.basicBlock
				.getStateFromMeta(4), false);

		this.spawnVillagers(world, box, 4, 1, 2, 1);
		return true;
	}

	protected void placeDoor (World worldIn, StructureBoundingBox boundingBoxIn, Random rand, int x, int y, int z, EnumFacing facing, EnumHingePosition hinge) {
		this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.FACING, facing)
				.withProperty(BlockDoor.HINGE, hinge), x, y, z, boundingBoxIn);
		this.setBlockState(worldIn, Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.FACING, facing)
				.withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER)
				.withProperty(BlockDoor.HINGE, hinge), x, y + 1, z, boundingBoxIn);
	}

	@Override
	protected VillagerProfession chooseForgeProfession (int count, net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession prof) {
		return ExoVillagerHandler.PROF_ASTROLOGIST;
	}

	public static class VillageManager implements IVillageCreationHandler {
		@Override
		public Village buildComponent (PieceWeight villagePiece, Start startPiece, List<StructureComponent> pieces, Random random, int p1, int p2, int p3, EnumFacing facing, int p5) {
			StructureBoundingBox box = StructureBoundingBox
					.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, 11, 10, 9, facing);
			return (!canVillageGoDeeper(box)) || (StructureComponent.findIntersecting(pieces, box) != null) ? null
					: new VillageAstronomerHouse(startPiece, p5, random, box, facing);
		}

		@Override
		public PieceWeight getVillagePieceWeight (Random random, int i) {
			return new PieceWeight(VillageAstronomerHouse.class, 15, MathHelper.getInt(random, 0 + i, 1 + i));
		}

		@Override
		public Class<?> getComponentClass () {
			return VillageAstronomerHouse.class;
		}
	}
}
