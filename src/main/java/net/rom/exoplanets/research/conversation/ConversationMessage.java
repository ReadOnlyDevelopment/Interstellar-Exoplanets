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

package net.rom.exoplanets.research.conversation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.api.research.conversation.IConversationMessage;
import net.rom.exoplanets.api.research.conversation.IConversationMessageSeedable;
import net.rom.exoplanets.api.research.conversation.IConversationNpc;
import net.rom.exoplanets.api.research.conversation.IConversationOption;
import net.rom.exoplanets.api.research.exception.ExoRuntimeException;
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
