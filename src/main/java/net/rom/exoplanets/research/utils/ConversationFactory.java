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
