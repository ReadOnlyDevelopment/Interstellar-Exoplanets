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

package net.rom.exoplanets.util;

import java.util.Locale;
import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

import lombok.Setter;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.registries.IForgeRegistryEntry;

@ParametersAreNonnullByDefault
public class TranslateUtil {
    private final String modId;

    private boolean replacesColons = true;

    public TranslateUtil(String modId) {
        this.modId = modId;
    }

    /**
     * Gets an appropriate translation key in the form {@code "prefix.modId.key"}
     *
     * @return The translation key
     */
    public String getKey(String prefix, String key) {
        return prefix + "." + modId + "." + key;
    }

    /**
     * Check whether or not the key is in the translation file. You do not need to call this in most
     * cases, translation attempts just return the key if it is not found.
     *
     * @param key The key, checked as-is
     * @return If the key exists
     */
    public boolean hasKey(String key) {
        return I18n.hasKey(key);
    }

    /**
     * Basic translation using the key as provided (does not add mod ID or anything else)
     *
     * @param key    Translation key
     * @param params Optional parameters to format into translation
     * @return Translation result, or {@code key} if the key is not found
     */
    public String translate(String key, Object... params) {
        if (replacesColons) key = key.replace(':', '.');

        return I18n.format(key, params);
    }

    /**
     * Translates the text with key "prefix.mod_id.key"
     *
     * @param prefix Key prefix (item, tile, etc.)
     * @param key    Key suffix
     * @param params Optional parameters to format into translation
     * @return Translation result, or {@code key} if the key is not found
     */
    public String translate(String prefix, String key, Object... params) {
        return translate(getKey(prefix, key), params);
    }
}
