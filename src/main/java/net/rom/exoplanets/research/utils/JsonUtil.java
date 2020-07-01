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

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.rom.api.research.exception.ExoRuntimeException;
import net.rom.exoplanets.ExoplanetsMod;

public class JsonUtil {
    private static String currentParentObject;

    public static boolean getBool(JsonObject jsonObject, String key, boolean def) {
        if (jsonObject.has(key)) {
            JsonElement element = jsonObject.get(key);
            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isBoolean()) {
                return element.getAsBoolean();
            }
        }
        return def;
    }

    public static int getInt(JsonObject jsonObject, String key, int def) {
        if (jsonObject.has(key)) {
            JsonElement element = jsonObject.get(key);
            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber()) {
                return element.getAsInt();
            }
        }
        return def;
    }

    public static int getInt(JsonObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            return jsonObject.get(key).getAsInt();
        } else {
            throw new ExoRuntimeException(String.format("Could not find key: '%s' in JSON Object '%s'", key, currentParentObject));
        }
    }

    public static String getString(JsonObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            return jsonObject.get(key).getAsString();
        } else {
            throw new ExoRuntimeException(String.format("Could not find key: '%s' in JSON Object '%s'", key, currentParentObject));
        }
    }

    public static String[] getStringArray(JsonObject jsonObject, String key) {
        if (jsonObject.has(key)) {
            JsonArray array = jsonObject.getAsJsonArray(key);
            String[] strings = new String[array.size()];
            for (int i = 0; i < array.size(); i++) {
                strings[i] = array.get(i).getAsString();
            }
            return strings;
        } else {
            throw new ExoRuntimeException(String.format("Could not find key: '%s' in JSON Object '%s'", key, currentParentObject));
        }
    }

    public static String getString(JsonObject jsonObject, String key, String def) {
        if (jsonObject.has(key)) {
            JsonElement element = jsonObject.get(key);
            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
                return element.getAsJsonPrimitive().getAsString();
            }
        }
        return def;
    }

    public static ItemStack getItemStack(JsonObject jsonObject, String key, ItemStack def) {
        if (jsonObject.has(key)) {
            JsonElement element = jsonObject.get(key);
            if (element.isJsonObject()) {
                JsonObject obj = element.getAsJsonObject();
                Item item = Item.getByNameOrId(obj.get("id").getAsString());
                int count = getInt(obj, "count", 1);
                int damage = getInt(obj, "damage", 0);
                ItemStack itemStack = new ItemStack(item, count, damage);
                if (obj.has("nbt")) {
                    try {
                        NBTTagCompound tagCompound = JsonToNBT.getTagFromJson(obj.get("nbt").toString());
                        itemStack.setTagCompound(tagCompound);
                    } catch (NBTException e) {
                    	ExoplanetsMod.logger.formatted_Error("Could not parse NBT tag from Json in '%s'", e);
                    }
                }
                return itemStack;
            }
        }
        return def;
    }

    public static Vec3d getVec3(JsonObject jsonObject, String key, Vec3d def) {
        if (jsonObject.has(key)) {
            JsonElement element = jsonObject.get(key);
            if (element.isJsonArray()) {
                JsonArray array = element.getAsJsonArray();
                if (array.size() == 3) {
                    try {
                        double x = array.get(0).getAsDouble();
                        double y = array.get(1).getAsDouble();
                        double z = array.get(2).getAsDouble();
                        return new Vec3d(x, y, z);
                    } catch (Exception e) {
                    	ExoplanetsMod.logger.formatted_Error("All elements in Vec3 array must be decimals", e);
                    }
                }
            }
        }
        return def;
    }

    public static NBTTagCompound getNbt(JsonObject jsonObject, String key, NBTTagCompound def) {
        if (jsonObject.has(key)) {
            try {
                String json = jsonObject.get(key).toString();
                NBTTagCompound tagCompound = JsonToNBT.getTagFromJson(json);
                removeDoubleQuotes(tagCompound);
                return tagCompound;
            } catch (NBTException e) {
            	ExoplanetsMod.logger.formatted_Error("Could not parse NBT tag from Json", e);
            }
        }
        return def;
    }

    public static BlockPos getPos(JsonObject jsonObject, String key, BlockPos def) {
        if (jsonObject.has(key)) {
            JsonArray array = jsonObject.getAsJsonArray(key);
            if (array.size() == 3) {
                return new BlockPos(array.get(0).getAsInt(), array.get(1).getAsInt(), array.get(2).getAsInt());
            }
        }
        return def;
    }

    public static void removeDoubleQuotes(NBTTagCompound tagCompound) {
        List<String> cachedKeyList = new ArrayList<>();
        cachedKeyList.addAll(tagCompound.getKeySet());
        for (String key : cachedKeyList) {
            NBTBase base = tagCompound.getTag(key);
            tagCompound.removeTag(key);

            key = key.replace("\"", "");
            if (base instanceof NBTTagCompound) {
                removeDoubleQuotes((NBTTagCompound) base);
            } else if (base instanceof NBTTagList) {
                removeDoubleQuotes((NBTTagList) base);
            }
            tagCompound.setTag(key, base);
        }
    }

    public static void removeDoubleQuotes(NBTTagList tagList) {
        for (int i = 0; i < tagList.tagCount(); i++) {
            if (tagList.get(i) instanceof NBTTagCompound) {
                removeDoubleQuotes((NBTTagCompound) tagList.get(i));
            } else if (tagList.get(i) instanceof NBTTagList) {
                removeDoubleQuotes((NBTTagList) tagList.get(i));
            }
        }
    }

    public static void setCurrentParentObject(String parentObject) {
        JsonUtil.currentParentObject = parentObject;
    }
}
