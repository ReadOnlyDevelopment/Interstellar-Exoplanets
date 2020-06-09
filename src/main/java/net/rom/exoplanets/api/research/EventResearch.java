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

package net.rom.exoplanets.api.research;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public class EventResearch extends PlayerEvent {
    public final ResearchStack researchStack;

    public EventResearch(ResearchStack researchStack, EntityPlayer entityPlayer) {
        super(entityPlayer);
        this.researchStack = researchStack;
    }
    
    @Cancelable
    public static class Completed extends EventResearch {
        public int xp;
        public List<IResearchReward> rewards;

        public Completed(ResearchStack researchStack, EntityPlayer entityPlayer, int xp, List<IResearchReward> rewards) {
            super(researchStack, entityPlayer);
            this.xp = xp;
            this.rewards = rewards;
        }
    }
    
    @Cancelable
    public static class Added extends EventResearch {
        public Added(ResearchStack researchStack, EntityPlayer entityPlayer) {
            super(researchStack, entityPlayer);
        }
    }
}
