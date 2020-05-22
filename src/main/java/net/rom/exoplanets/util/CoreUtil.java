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

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class CoreUtil {

    public static int hexToRgb(String color) {
        return rgbToDecimal(Integer.valueOf(color.substring(1, 3), 16), Integer.valueOf(color.substring(3, 5), 16), Integer.valueOf(color.substring(5, 7), 16));
    }

    public static int rgbToDecimal(int r, int g, int b) {
        return b + 256 * g + 65536 * r;
    }

    public static RGB stringToRGB(String color) {
        return stringToRGB(color, false, null);
    }

    public static RGB stringToRGB(String color, boolean printException, String optionName) {
        try {
            String[] colorArray = color.split(",");
            float red = Float.parseFloat(colorArray[0]);
            float green = Float.parseFloat(colorArray[1]);
            float blue = Float.parseFloat(colorArray[2]);
            return new RGB(red, green, blue, 255.0F);
        } catch (Exception e) {
            if (printException) {
                e.printStackTrace();
            }
            return new RGB(true);
        }
    }

    public static RGB toRGB(int color) {
        float alpha = (color >> 24 & 255) / 255.0F;
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        return new RGB(red, green, blue, alpha);
    }

    public static class RGB {

        float red;
        float green;
        float blue;
        float alpha;
        boolean error;

        public RGB(float red, float green, float blue, float alpha) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }

        RGB(boolean error) {
            this.error = error;
        }

        public int packedRed() {
            return (int) (this.red * 255.0F);
        }

        public int packedGreen() {
            return (int) (this.green * 255.0F);
        }

        public int packedBlue() {
            return (int) (this.blue * 255.0F);
        }

        public int packedAlpha() {
            return (int) (this.alpha * 255.0F);
        }

        public float floatRed() {
            return this.red / 255.0F;
        }

        public float floatGreen() {
            return this.green / 255.0F;
        }

        public float floatBlue() {
            return this.blue / 255.0F;
        }

        public float floatAlpha() {
            return this.alpha / 255.0F;
        }

        public int red() {
            return (int) this.red;
        }

        public int green() {
            return (int) this.green;
        }

        public int blue() {
            return (int) this.blue;
        }

        public int alpha() {
            return (int) this.alpha;
        }
    }
    
	public static <T extends Entity> void registerEntityRenderer(Class<T> entityClass, IRenderFactory<? super T> renderFactory) {
		RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
	}

}
