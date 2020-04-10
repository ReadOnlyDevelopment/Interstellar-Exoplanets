package net.rom.exoplanets.internal.item;

import java.awt.Color;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.rom.exoplanets.internal.client.ClientTicks;

public interface ICustomEnchantColor {
    // Some NBT keys used to control color.
    String NBT_QUARK_RUNE_ATTACHED = "Quark:RuneAttached";
    String NBT_QUARK_RUNE_COLOR = "Quark:RuneColor";

    /**
     * Get the untruncated enchanted effect color. This defaults to checking for two NBT tags, one
     * added by Silent Lib and the other for Quark runes. See the constants above.
     *
     * @param stack The item
     * @return The effect color
     */
    default int getEffectColor(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();

        if (tagCompound != null) {
            if (tagCompound.hasKey(NBT_QUARK_RUNE_ATTACHED) && tagCompound.hasKey(NBT_QUARK_RUNE_COLOR)) {
                // Quark runes - stored int is either dye metadata or 16 for rainbow
                int value = tagCompound.getInteger(NBT_QUARK_RUNE_COLOR);
                if (value > 15)
                    return Color.HSBtoRGB(ClientTicks.totalTicks * 0.005f, 1f, 0.6f);
                else if (value >= 0)
                    return EnumDyeColor.byMetadata(value).getColorValue();
            }
        }

        return 0xFFFFFF;
    }

    /**
     * Whether or not the effect color should have its brightness truncated. Without this, the
     * effect would be fully opaque.
     *
     * @param stack The item
     * @return If brightness should be truncated
     */
    default boolean shouldTruncateBrightness(ItemStack stack) {
        return true;
    }

    /**
     * Get the max brightness of the effect, if {@link #shouldTruncateBrightness(ItemStack)} returns
     * true. Default value (396) is roughly equivalent to vanilla.
     *
     * @param stack The item
     * @return The max brightness
     */
    default int getEffectMaxBrightness(ItemStack stack) {
        return 396;
    }
}
