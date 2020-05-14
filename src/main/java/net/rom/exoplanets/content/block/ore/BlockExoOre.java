/*
 * Fun Ores -- BlockFunOre
 * Copyright (C) 2018 SilentChaos512
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation version 3
 * of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.rom.exoplanets.content.block.ore;

import net.minecraft.block.material.Material;
import net.rom.exoplanets.internal.IAddRecipe;
import net.rom.exoplanets.internal.block.BlockMetaSubtypes;
import net.rom.exoplanets.util.CreativeExoTabs;

public abstract class BlockExoOre extends BlockMetaSubtypes implements IAddRecipe {

    public final int maxMeta;

    public BlockExoOre(int subBlockCount) {
        super(Material.ROCK, subBlockCount);
        this.maxMeta = subBlockCount;
        setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);
    }

}
