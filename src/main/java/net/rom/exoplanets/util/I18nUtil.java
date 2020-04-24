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
public class I18nUtil {
    private final String modId;


    /**
     * If true, any colons (':') in translation keys will be replaced with dots ('.')
     *
     */
    @Setter
    private boolean replacesColons = true;

    public I18nUtil(String modId) {
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
     * Gets an appropriate translation key in the form {@code "prefix.modId.key.suffix"}
     *
     * @return The translation key
     */
    public String getKey(String prefix, String key, String suffix) {
        return prefix + "." + modId + "." + key + "." + suffix;
    }

    public String getKey(String prefix, ResourceLocation name) {
        return prefix + "." + name.getResourceDomain() + "." + name.getResourcePath();
    }

    public String getKey(IForgeRegistryEntry<?> object, String key) {
        String prefix = getPrefixFor(object);
        ResourceLocation name = Objects.requireNonNull(object.getRegistryName());
        return prefix + "." + name.getResourceDomain() + "." + name.getResourcePath() + "." + key;
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

    public String translatedName(Block block) {
        return translate(block.getUnlocalizedName() + ".name");
    }

    public String translatedName(Item item) {
        return translate(item.getUnlocalizedName() + ".name");
    }

    public String translatedName(ItemStack stack) {
        return translate(stack.getUnlocalizedName() + ".name");
    }

    /**
     * Translates the text with key "(prefix).registry_name.key". This uses the object's registry
     * name namespace instead of {@link #modId}. Prefix is determined by the object's type.
     *
     * @param object An {@link IForgeRegistryEntry} of some kind, such as a {@link Block} or {@link
     *               Item}
     * @param key    Key suffix
     * @param params Optional parameters to format into translation
     * @return Translation result, or {@code key} if the key is not found
     */
    public String subText(IForgeRegistryEntry<?> object, String key, Object... params) {
        return translate(getKey(object, key), params);
    }

    /**
     * Translates the text with key "prefix.mod_id.objName.key".
     *
     * @param objName Object name (registry name), minus mod ID
     * @param prefix  Key prefix (item, tile, etc.)
     * @param key     Key suffix
     * @param params  Optional parameters to format into translation
     * @return Translation result, or {@code key} if the key is not found
     */
    public String subText(String objName, String prefix, String key, Object... params) {
        return translate(getKey(prefix, objName, key), params);
    }

    /**
     * Gets {@link #subText(String, String, String, Object...)} with prefix "tile".
     *
     * @param blockName Block name, minus mod ID
     * @param key       Key suffix
     * @param params    Optional parameters to format into translation
     * @return Translation result, or {@code key} if the key is not found
     */
    public String blockSubText(String blockName, String key, Object... params) {
        return subText(blockName, "tile", key, params);
    }

    /**
     * Gets {@link #subText(String, String, String, Object...)} with prefix "item".
     *
     * @param itemName Item name, minus mod ID
     * @param key      Key suffix
     * @param params   Optional parameters to format into translation
     * @return Translation result, or {@code key} if the key is not found
     */
    public String itemSubText(String itemName, String key, Object... params) {
        return subText(itemName, "item", key, params);
    }

    public String miscText(String key, Object... params) {
        return translate("misc", key, params);
    }

    /**
     * Creates a {@link TextComponentTranslation}, suitable for sending text from server to client.
     *
     * @param prefix Key prefix (item, tile, etc.)
     * @param key    Key suffix (after mod ID)
     * @param params Optional parameters to format into translation
     * @return An {@link ITextComponent}
     */
    public ITextComponent textComponent(String prefix, String key, Object... params) {
        return new TextComponentTranslation(getKey(prefix, key), params);
    }

    /**
     * Creates a {@link TextComponentTranslation}, suitable for sending text from server to client.
     *
     * @param prefix Key prefix (item, tile, etc.)
     * @param key    Key middle (after mod ID)
     * @param suffix Key suffix (after {@code key})
     * @param params Optional parameters to format into translation
     * @return An {@link ITextComponent}
     */
    public ITextComponent textComponent(String prefix, String key, String suffix, Object... params) {
        return new TextComponentTranslation(getKey(prefix, key, suffix), params);
    }

    private String getPrefixFor(IForgeRegistryEntry<?> object) {
        if (object instanceof Item)
            return "item";
        if (object instanceof Block)
            return "tile";
        return object.getClass().getName().toLowerCase(Locale.ROOT);
    }
}
