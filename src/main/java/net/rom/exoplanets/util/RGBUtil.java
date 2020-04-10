package net.rom.exoplanets.util;

public class RGBUtil {
	
	protected static float[] rgbArray = new float[3];
	

	
    /**
     * Convert an RGB value
     *
     * @param red
     * @param green
     * @param blue
     */
    public static int RGB(int red, int green, int blue)
    {
        return RGBA(red, green, blue, 255);
    }
    
    /**
     * Convert an RGB value
     *
     * @param red
     * @param green
     * @param blue
     */
    public static int RGB(float red, float green, float blue)
    {
        return RGBA((int) red * 255, (int) green * 255, (int) blue * 255, 255);
    }
    
    /**
     * Convert an #RRGGBB value
     *
     * @param colour #RRGGBB value
     * @throws IllegalArgumentException if a malformed input is given
     */
    public static int RGB(String colour)
    {
        if (!colour.startsWith("#") || !(colour.length() == 7))
        {
            throw new IllegalArgumentException("Use #RRGGBB format");
        }
        return RGB(Integer.parseInt(colour.substring(1, 3), 16), Integer.parseInt(colour.substring(3, 5), 16), Integer.parseInt(colour.substring(5, 7), 16));
    }
    
    /**
     * Convert RGBA value
     *
     * @param int - red
     * @param int - green
     * @param int - blue
     * @param int - alpha
     */
    public static int RGBA(int red, int green, int blue, int alpha)
    {
        return (alpha << 24) | ((red & 255) << 16) | ((green & 255) << 8) | ((blue & 255));
    }


}
