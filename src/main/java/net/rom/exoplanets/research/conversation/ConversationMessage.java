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

package net.rom.exoplanets.research.conversation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.api.research.conversation.IConversationMessage;
import net.rom.api.research.conversation.IConversationMessageSeedable;
import net.rom.api.research.conversation.IConversationNpc;
import net.rom.api.research.conversation.IConversationOption;
import net.rom.api.research.exception.ExoRuntimeException;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.research.utils.JsonUtil;

public class ConversationMessage implements IConversationMessage, IConversationMessageSeedable {
	protected static Random random = new Random();
	protected String[] messages;
	protected String[] researchions;
	protected boolean unlocalized;
	protected IConversationMessage parent;
	protected List<IConversationOption> options;
	@SideOnly(Side.CLIENT)
	protected String holoIcon;
	protected long seed;

	public ConversationMessage(JsonObject object) {
		init();
		this.unlocalized = JsonUtil.getBool(object, "unlocalized", false);
		if (object.has("message")) {
			JsonElement messageElement = object.get("message");
			if (messageElement.isJsonArray()) {
				messages = JsonUtil.getStringArray(object, "message");
			} else if (messageElement.isJsonPrimitive() && messageElement.getAsJsonPrimitive().isString()) {
				messages = new String[] { messageElement.getAsString() };
			}
		} else {
			throw new ExoRuntimeException(String.format("Cannot find Message for Conversation"));
		}
		if (object.has("researchion")) {
			JsonElement researchionElement = object.get("researchion");
			if (researchionElement.isJsonArray()) {
				researchions = JsonUtil.getStringArray(object, "message");
			} else if (researchionElement.isJsonPrimitive() && researchionElement.getAsJsonPrimitive().isString()) {
				researchions = new String[] { researchionElement.getAsString() };
			}
		}
	}

	public ConversationMessage() {
		init();
	}

	public ConversationMessage(String message) {
		this(message, message);
	}

	public ConversationMessage(String message, String researchion) {
		this(message != null ? new String[] { message } : null, researchion != null ? new String[] { researchion } : null);
	}

	public ConversationMessage(String[] messages, String[] researchions) {
		this.messages = messages;
		this.researchions = researchions;
		init();
	}

	private void init() {
		options = new ArrayList<>();
	}

	@Override
	public IConversationMessage getParent(IConversationNpc npc, EntityPlayer player) {
		return parent;
	}

	@Override
	public List<IConversationOption> getOptions(IConversationNpc npc, EntityPlayer player) {
		return options;
	}

	@Override
	public String getMessageText(IConversationNpc npc, EntityPlayer player) {
		if (messages != null && messages.length > 0) {
			int messageIndex = 0;
			if (messages.length > 1) {
				messageIndex = random.nextInt(messages.length);
			}
			return formatMessage(messages[messageIndex], npc, player);
		}
		return "";
	}

	@Override
	public String getResearchionText(IConversationNpc npc, EntityPlayer player) {
		if (researchions != null && researchions.length > 0) {
			int researchionIndex = 0;
			if (researchions.length > 1) {
				researchionIndex = random.nextInt(researchions.length);
			}
			return formatResearchion(researchions[researchionIndex], npc, player);
		}
		return "";
	}

	@Override
	public void onOptionsInteract(IConversationNpc npc, EntityPlayer player, int option) {
		if (option >= 0 && option < options.size()) {
			options.get(option).onInteract(npc, player);
		}
	}

	@Override
	public void onInteract(IConversationNpc npc, EntityPlayer player) {
		// TODO finish
	}

	@SideOnly(Side.CLIENT)
	protected void setAsGuiActiveMessage(IConversationNpc npc, EntityPlayer player) {
		// TODO finish
	}

	@Override
	public boolean canInteract(IConversationNpc npc, EntityPlayer player) {
		return true;
	}

	@Override
	public boolean isVisible(IConversationNpc npc, EntityPlayer player) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getHoloIcon(IConversationNpc npc, EntityPlayer player) {
		return holoIcon;
	}

	@Override
	public boolean equalsOption(IConversationOption other) {
		return this.equals(other);
	}

	public void setParent(IConversationMessage parent) {
		this.parent = parent;
	}

	public void addOption(IConversationOption message) {
		this.options.add(message);
	}

	public IConversationOption getOption(int id) {
		return this.options.get(id);
	}

	public List<IConversationOption> getOptions() {
		return options;
	}

	@SideOnly(Side.CLIENT)
	public ConversationMessage setHoloIcon(String holoIcon) {
		this.holoIcon = holoIcon;
		return this;
	}

	protected String formatMessage(String text, IConversationNpc npc, EntityPlayer player) {
		if (text != null) {
			return String.format(unlocalized ? ExoplanetsMod.translate.translate(text) : text, player.getDisplayName().getFormattedText(), npc.getEntity().getDisplayName()
					.getFormattedText());
		}
		return null;
	}

	protected String formatResearchion(String text, IConversationNpc npc, EntityPlayer player) {
		if (text != null) {
			return String.format(unlocalized ? ExoplanetsMod.translate.translate(text) : text, player.getDisplayName().getFormattedText(), npc.getEntity().getDisplayName()
					.getFormattedText());
		}
		return null;
	}

	public ConversationMessage setUnlocalized(boolean unlocalized) {
		this.unlocalized = unlocalized;
		return this;
	}

	@Override
	public void setSeed(long seed) {
		this.seed = seed;
		random.setSeed(seed);
	}

	public void setResearchions(String[] researchions) {
		this.researchions = researchions;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}
}
