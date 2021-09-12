package com.readonlydev.core.registries;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import com.readonlydev.common.item.ItemExoDust;
import com.readonlydev.common.item.ItemExoIngot;
import com.readonlydev.common.item.ItemGear;
import com.readonlydev.common.item.ItemSheet;
import com.readonlydev.common.item.ItemTwoPlayerRocket;
import com.readonlydev.lib.registry.InterstellarRegistry;
import com.readonlydev.lib.registry.impl.ItemRegistry;

import net.minecraft.item.Item;

public class ExoplanetItems extends ItemRegistry {
	
	public static final Item TWOPERSONROCKET = new ItemTwoPlayerRocket();
	public static final Item METAL_DUST       = new ItemExoDust(false);
	public static final Item ALLOY_DUST       = new ItemExoDust(true);
	
	public static final Item METAL_INGOT     = new ItemExoIngot(false);
	public static final Item ALLOY_INGOT     = new ItemExoIngot(true);

	public static final Item METAL_SHEET     = new ItemSheet(false);
	public static final Item ALLOY_SHEET     = new ItemSheet(true);
	
	public static final Item METAL_GEAR      = new ItemGear(false);
	public static final Item ALLOY_GEAR      = new ItemGear(true);

	public static final Set<Item> ITEMS = new LinkedHashSet<>();

	@Override
	public void register(InterstellarRegistry registry) {
		try {
			for (Field field : ExoplanetItems.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Item) {
					Item block = (Item) obj;
					String name = field.getName().toLowerCase(Locale.ROOT);
					registry.register(block, name);
					ITEMS.add(block);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
