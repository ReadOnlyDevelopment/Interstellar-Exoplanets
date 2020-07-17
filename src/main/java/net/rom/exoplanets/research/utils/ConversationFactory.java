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

package net.rom.exoplanets.research.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.api.research.conversation.IConversationNpc;
import net.rom.exoplanets.api.research.conversation.IConversationRegistry;
import net.rom.exoplanets.research.conversation.ConversationMessage;

public class ConversationFactory {
    private final IConversationRegistry registry;

    public ConversationFactory(IConversationRegistry registry) {
        this.registry = registry;
    }

    public ConversationMessage[] constructMultipleLineConversation(Class<? extends ConversationMessage> mainMessageType, String unlocalizedName, int lines, String nextLineResearchion) {

        ConversationMessage[] messages = new ConversationMessage[lines];
        try {
            messages[0] = mainMessageType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            messages[0] = new ConversationMessage();
        } finally {
            registry.registerMessage(messages[0]);
        }
        messages[0].setMessages(new String[]{String.format("%s.%s.line", unlocalizedName, 0)});
        messages[0].setResearchions(new String[]{unlocalizedName + ".researchion"});
        messages[0].setUnlocalized(true);

        ConversationMessage lastChild = messages[0];
        for (int i = 1; i < lines; i++) {
            ConversationMessage child = new ConversationMessage("", nextLineResearchion);
            registry.registerMessage(child);
            child.setMessages(new String[]{String.format("%s.%s.line", unlocalizedName, i)});
            if (ExoplanetsMod.translate.hasKey(String.format("%s.%s.researchion", unlocalizedName, i))) {
                child.setResearchions(new String[]{String.format("%s.%s.researchion", unlocalizedName, i)});
            }
            child.setUnlocalized(true);
            child.setParent(lastChild);
            lastChild.addOption(child);
            lastChild = child;
            messages[i] = child;
        }

        return messages;
    }

    public ConversationMessage addOnlyVisibleOptions(EntityPlayer entityPlayer, IConversationNpc ConversationNpc, ConversationMessage parent, ConversationMessage... options) {
        for (ConversationMessage option : options) {
            if (option.isVisible(ConversationNpc, entityPlayer)) {
                parent.addOption(option);
            }
        }
        return parent;
    }
}
