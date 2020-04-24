package net.rom.exoplanets.item;

import java.util.Locale;

import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.rom.exoplanets.internal.item.IEnumItems;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public enum EnumIngots implements IStringSerializable, IEnumItems<EnumIngots, EnumIngots.ItemIngot> {

	RUTILE("Rutile"), LIMONITE("Limonite"), RUTHENIUM("Ruthenium");

	private ItemIngot item;
	private String name;

	EnumIngots(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name.toLowerCase(Locale.ROOT);
	}

	@Override
	public EnumIngots getEnum() {
		return this;
	}

	@Override
	public ItemIngot getItem() {
		if (item == null)
			item = new ItemIngot();
		return item;
	}

	public class ItemIngot extends Item {
		public ItemIngot() {
			setCreativeTab(CreativeExoTabs.ITEMS_CREATIVE_TABS);
		}
	}
}
