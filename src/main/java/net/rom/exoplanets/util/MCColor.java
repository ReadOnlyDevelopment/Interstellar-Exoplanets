package net.rom.exoplanets.util;

public class MCColor {
	
	private MCColor() {};
	
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

}
