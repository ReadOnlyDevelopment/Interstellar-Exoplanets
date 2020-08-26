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
package net.rom95.client;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.primitives.UnsignedInts;
import com.google.gson.JsonObject;

import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.MathHelper;

public class RGB {
	private static final Map<String, RGB> NAMED_MAP            = new HashMap<>();
	private static final Pattern          PATTERN_LEADING_JUNK = Pattern.compile("(#|0x)", Pattern.CASE_INSENSITIVE);
	private static final Pattern          PATTERN_HEX_CODE     = Pattern
			.compile("(#|0x)?[0-9a-f]{1,8}", Pattern.CASE_INSENSITIVE);

	public static final int VALUE_WHITE = 0xFFFFFF;

	//region CSS Colors
	public static final RGB ALICEBLUE            = named("AliceBlue", 0xF0F8FF);
	public static final RGB ANTIQUEWHITE         = named("AntiqueWhite", 0xFAEBD7);
	public static final RGB AQUA                 = named("Aqua", 0x00FFFF);
	public static final RGB AQUAMARINE           = named("Aquamarine", 0x7FFFD4);
	public static final RGB AZURE                = named("Azure", 0xF0FFFF);
	public static final RGB BEIGE                = named("Beige", 0xF5F5DC);
	public static final RGB BISQUE               = named("Bisque", 0xFFE4C4);
	public static final RGB BLACK                = named("Black", 0x000000);
	public static final RGB BLANCHEDALMOND       = named("BlanchedAlmond", 0xFFEBCD);
	public static final RGB BLUE                 = named("Blue", 0x0000FF);
	public static final RGB BLUEVIOLET           = named("BlueViolet", 0x8A2BE2);
	public static final RGB BROWN                = named("Brown", 0xA52A2A);
	public static final RGB BURLYWOOD            = named("BurlyWood", 0xDEB887);
	public static final RGB CADETBLUE            = named("CadetBlue", 0x5F9EA0);
	public static final RGB CHARTREUSE           = named("Chartreuse", 0x7FFF00);
	public static final RGB CHOCOLATE            = named("Chocolate", 0xD2691E);
	public static final RGB CORAL                = named("Coral", 0xFF7F50);
	public static final RGB CORNFLOWERBLUE       = named("CornflowerBlue", 0x6495ED);
	public static final RGB CORNSILK             = named("Cornsilk", 0xFFF8DC);
	public static final RGB CRIMSON              = named("Crimson", 0xDC143C);
	public static final RGB CYAN                 = named("Cyan", 0x00FFFF);
	public static final RGB DARKBLUE             = named("DarkBlue", 0x00008B);
	public static final RGB DARKCYAN             = named("DarkCyan", 0x008B8B);
	public static final RGB DARKGOLDENROD        = named("DarkGoldenRod", 0xB8860B);
	public static final RGB DARKGRAY             = named("DarkGray", 0xA9A9A9);
	public static final RGB DARKGREY             = named("DarkGrey", 0xA9A9A9);
	public static final RGB DARKGREEN            = named("DarkGreen", 0x006400);
	public static final RGB DARKKHAKI            = named("DarkKhaki", 0xBDB76B);
	public static final RGB DARKMAGENTA          = named("DarkMagenta", 0x8B008B);
	public static final RGB DARKOLIVEGREEN       = named("DarkOliveGreen", 0x556B2F);
	public static final RGB DARKORANGE           = named("DarkOrange", 0xFF8C00);
	public static final RGB DARKORCHID           = named("DarkOrchid", 0x9932CC);
	public static final RGB DARKRED              = named("DarkRed", 0x8B0000);
	public static final RGB DARKSALMON           = named("DarkSalmon", 0xE9967A);
	public static final RGB DARKSEAGREEN         = named("DarkSeaGreen", 0x8FBC8F);
	public static final RGB DARKSLATEBLUE        = named("DarkSlateBlue", 0x483D8B);
	public static final RGB DARKSLATEGRAY        = named("DarkSlateGray", 0x2F4F4F);
	public static final RGB DARKSLATEGREY        = named("DarkSlateGrey", 0x2F4F4F);
	public static final RGB DARKTURQUOISE        = named("DarkTurquoise", 0x00CED1);
	public static final RGB DARKVIOLET           = named("DarkViolet", 0x9400D3);
	public static final RGB DEEPPINK             = named("DeepPink", 0xFF1493);
	public static final RGB DEEPSKYBLUE          = named("DeepSkyBlue", 0x00BFFF);
	public static final RGB DIMGRAY              = named("DimGray", 0x696969);
	public static final RGB DIMGREY              = named("DimGrey", 0x696969);
	public static final RGB DODGERBLUE           = named("DodgerBlue", 0x1E90FF);
	public static final RGB FIREBRICK            = named("FireBrick", 0xB22222);
	public static final RGB FLORALWHITE          = named("FloralWhite", 0xFFFAF0);
	public static final RGB FORESTGREEN          = named("ForestGreen", 0x228B22);
	public static final RGB FUCHSIA              = named("Fuchsia", 0xFF00FF);
	public static final RGB GAINSBORO            = named("Gainsboro", 0xDCDCDC);
	public static final RGB GHOSTWHITE           = named("GhostWhite", 0xF8F8FF);
	public static final RGB GOLD                 = named("Gold", 0xFFD700);
	public static final RGB GOLDENROD            = named("GoldenRod", 0xDAA520);
	public static final RGB GRAY                 = named("Gray", 0x808080);
	public static final RGB GREY                 = named("Grey", 0x808080);
	public static final RGB GREEN                = named("Green", 0x008000);
	public static final RGB GREENYELLOW          = named("GreenYellow", 0xADFF2F);
	public static final RGB HONEYDEW             = named("HoneyDew", 0xF0FFF0);
	public static final RGB HOTPINK              = named("HotPink", 0xFF69B4);
	public static final RGB INDIANRED            = named("IndianRed", 0xCD5C5C);
	public static final RGB INDIGO               = named("Indigo", 0x4B0082);
	public static final RGB IVORY                = named("Ivory", 0xFFFFF0);
	public static final RGB KHAKI                = named("Khaki", 0xF0E68C);
	public static final RGB LAVENDER             = named("Lavender", 0xE6E6FA);
	public static final RGB LAVENDERBLUSH        = named("LavenderBlush", 0xFFF0F5);
	public static final RGB LAWNGREEN            = named("LawnGreen", 0x7CFC00);
	public static final RGB LEMONCHIFFON         = named("LemonChiffon", 0xFFFACD);
	public static final RGB LIGHTBLUE            = named("LightBlue", 0xADD8E6);
	public static final RGB LIGHTCORAL           = named("LightCoral", 0xF08080);
	public static final RGB LIGHTCYAN            = named("LightCyan", 0xE0FFFF);
	public static final RGB LIGHTGOLDENRODYELLOW = named("LightGoldenRodYellow", 0xFAFAD2);
	public static final RGB LIGHTGRAY            = named("LightGray", 0xD3D3D3);
	public static final RGB LIGHTGREY            = named("LightGrey", 0xD3D3D3);
	public static final RGB LIGHTGREEN           = named("LightGreen", 0x90EE90);
	public static final RGB LIGHTPINK            = named("LightPink", 0xFFB6C1);
	public static final RGB LIGHTSALMON          = named("LightSalmon", 0xFFA07A);
	public static final RGB LIGHTSEAGREEN        = named("LightSeaGreen", 0x20B2AA);
	public static final RGB LIGHTSKYBLUE         = named("LightSkyBlue", 0x87CEFA);
	public static final RGB LIGHTSLATEGRAY       = named("LightSlateGray", 0x778899);
	public static final RGB LIGHTSLATEGREY       = named("LightSlateGrey", 0x778899);
	public static final RGB LIGHTSTEELBLUE       = named("LightSteelBlue", 0xB0C4DE);
	public static final RGB LIGHTYELLOW          = named("LightYellow", 0xFFFFE0);
	public static final RGB LIME                 = named("Lime", 0x00FF00);
	public static final RGB LIMEGREEN            = named("LimeGreen", 0x32CD32);
	public static final RGB LINEN                = named("Linen", 0xFAF0E6);
	public static final RGB MAGENTA              = named("Magenta", 0xFF00FF);
	public static final RGB MAROON               = named("Maroon", 0x800000);
	public static final RGB MEDIUMAQUAMARINE     = named("MediumAquaMarine", 0x66CDAA);
	public static final RGB MEDIUMBLUE           = named("MediumBlue", 0x0000CD);
	public static final RGB MEDIUMORCHID         = named("MediumOrchid", 0xBA55D3);
	public static final RGB MEDIUMPURPLE         = named("MediumPurple", 0x9370DB);
	public static final RGB MEDIUMSEAGREEN       = named("MediumSeaGreen", 0x3CB371);
	public static final RGB MEDIUMSLATEBLUE      = named("MediumSlateBlue", 0x7B68EE);
	public static final RGB MEDIUMSPRINGGREEN    = named("MediumSpringGreen", 0x00FA9A);
	public static final RGB MEDIUMTURQUOISE      = named("MediumTurquoise", 0x48D1CC);
	public static final RGB MEDIUMVIOLETRED      = named("MediumVioletRed", 0xC71585);
	public static final RGB MIDNIGHTBLUE         = named("MidnightBlue", 0x191970);
	public static final RGB MINTCREAM            = named("MintCream", 0xF5FFFA);
	public static final RGB MISTYROSE            = named("MistyRose", 0xFFE4E1);
	public static final RGB MOCCASIN             = named("Moccasin", 0xFFE4B5);
	public static final RGB NAVAJOWHITE          = named("NavajoWhite", 0xFFDEAD);
	public static final RGB NAVY                 = named("Navy", 0x000080);
	public static final RGB OLDLACE              = named("OldLace", 0xFDF5E6);
	public static final RGB OLIVE                = named("Olive", 0x808000);
	public static final RGB OLIVEDRAB            = named("OliveDrab", 0x6B8E23);
	public static final RGB ORANGE               = named("Orange", 0xFFA500);
	public static final RGB ORANGERED            = named("OrangeRed", 0xFF4500);
	public static final RGB ORCHID               = named("Orchid", 0xDA70D6);
	public static final RGB PALEGOLDENROD        = named("PaleGoldenRod", 0xEEE8AA);
	public static final RGB PALEGREEN            = named("PaleGreen", 0x98FB98);
	public static final RGB PALETURQUOISE        = named("PaleTurquoise", 0xAFEEEE);
	public static final RGB PALEVIOLETRED        = named("PaleVioletRed", 0xDB7093);
	public static final RGB PAPAYAWHIP           = named("PapayaWhip", 0xFFEFD5);
	public static final RGB PEACHPUFF            = named("PeachPuff", 0xFFDAB9);
	public static final RGB PERU                 = named("Peru", 0xCD853F);
	public static final RGB PINK                 = named("Pink", 0xFFC0CB);
	public static final RGB PLUM                 = named("Plum", 0xDDA0DD);
	public static final RGB POWDERBLUE           = named("PowderBlue", 0xB0E0E6);
	public static final RGB PURPLE               = named("Purple", 0x800080);
	public static final RGB REBECCAPURPLE        = named("RebeccaPurple", 0x663399);
	public static final RGB RED                  = named("Red", 0xFF0000);
	public static final RGB ROSYBROWN            = named("RosyBrown", 0xBC8F8F);
	public static final RGB ROYALBLUE            = named("RoyalBlue", 0x4169E1);
	public static final RGB SADDLEBROWN          = named("SaddleBrown", 0x8B4513);
	public static final RGB SALMON               = named("Salmon", 0xFA8072);
	public static final RGB SANDYBROWN           = named("SandyBrown", 0xF4A460);
	public static final RGB SEAGREEN             = named("SeaGreen", 0x2E8B57);
	public static final RGB SEASHELL             = named("SeaShell", 0xFFF5EE);
	public static final RGB SIENNA               = named("Sienna", 0xA0522D);
	public static final RGB SILVER               = named("Silver", 0xC0C0C0);
	public static final RGB SKYBLUE              = named("SkyBlue", 0x87CEEB);
	public static final RGB SLATEBLUE            = named("SlateBlue", 0x6A5ACD);
	public static final RGB SLATEGRAY            = named("SlateGray", 0x708090);
	public static final RGB SLATEGREY            = named("SlateGrey", 0x708090);
	public static final RGB SNOW                 = named("Snow", 0xFFFAFA);
	public static final RGB SPRINGGREEN          = named("SpringGreen", 0x00FF7F);
	public static final RGB STEELBLUE            = named("SteelBlue", 0x4682B4);
	public static final RGB TAN                  = named("Tan", 0xD2B48C);
	public static final RGB TEAL                 = named("Teal", 0x008080);
	public static final RGB THISTLE              = named("Thistle", 0xD8BFD8);
	public static final RGB TOMATO               = named("Tomato", 0xFF6347);
	public static final RGB TURQUOISE            = named("Turquoise", 0x40E0D0);
	public static final RGB VIOLET               = named("Violet", 0xEE82EE);
	public static final RGB WHEAT                = named("Wheat", 0xF5DEB3);
	public static final RGB WHITE                = named("White", 0xFFFFFF);
	public static final RGB WHITESMOKE           = named("WhiteSmoke", 0xF5F5F5);
	public static final RGB YELLOW               = named("Yellow", 0xFFFF00);
	public static final RGB YELLOWGREEN          = named("YellowGreen", 0x9ACD32);
	//endregion

	private final float red;
	private final float green;
	private final float blue;
	private final float alpha;

	public RGB(int color) {
		this((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF);
	}

	public RGB(int red, int green, int blue) {
		this(red / 255f, green / 255f, blue / 255f, 1f);
	}

	public RGB(int red, int green, int blue, int alpha) {
		this(red / 255f, green / 255f, blue / 255f, alpha / 255f);
	}

	public RGB(float red, float green, float blue) {
		this(red, green, blue, 1f);
	}

	public RGB(float red, float green, float blue, float alpha) {
		this.red   = red;
		this.green = green;
		this.blue  = blue;
		this.alpha = alpha;
	}

	private static RGB named (String name, int color) {
		RGB c = new RGB(color);
		NAMED_MAP.put(name.toLowerCase(Locale.ROOT), c);
		return c;
	}

	//region Parsing and Formatting

	/**
	 * Validator for color strings. Accepts CSS color names or hex codes with optional leading '#'
	 * or '0x'.
	 *
	 * @param str The string to validate
	 * @return True if and only if the string can be parsed
	 */
	public static boolean validate (String str) {
		return NAMED_MAP.containsKey(str.toLowerCase(Locale.ROOT)) || PATTERN_HEX_CODE.matcher(str).matches();
	}

	/**
	 * Format the color as it would appear in a config file.
	 *
	 * @param color The color
	 * @return A string in the form "#XXXXXX" or "#XXXXXXXX", where 'X' is a hex digit.
	 */
	public static String format (int color) {
		return String.format(color > 0xFFFFFF ? "#%08X" : "#%06X", color);
	}

	/**
	 * Attempt to parse the string as a color. May be either a common CSS color name or a hex code
	 * with optional leading '#' or '0x'. Consider using {@link #tryParse(String, int)} instead,
	 * which handles validation.
	 *
	 * @param str The string to try to parse
	 * @return A Color object based on str
	 * @throws NumberFormatException If the string cannot be parsed
	 * @throws NullPointerException  If the string is null
	 * @implNote Uses {@link UnsignedInts#parseUnsignedInt(String, int)} for parsing
	 */
	public static RGB parse (String str) {
		// Named color?
		str = str.toLowerCase(Locale.ROOT);
		if (NAMED_MAP.containsKey(str))
			return NAMED_MAP.get(str);

		// Hex code
		str = PATTERN_LEADING_JUNK.matcher(str).replaceFirst("");
		int color = UnsignedInts.parseUnsignedInt(str, 16);
		return new RGB(color);
	}

	/**
	 * Attempt to parse a color, returning the default if it is not valid.
	 *
	 * @param str          The string to parse
	 * @param defaultValue The fallback value
	 * @return A color parsed from str, or from defaultValue if str is invalid
	 */
	public static RGB tryParse (String str, int defaultValue) {
		if (!validate(str))
			return new RGB(defaultValue);
		return parse(str);
	}

	/**
	 * Read a color from JSON. The property must be a string. If the JsonObject does not contain a
	 * property with the given name, a Color is created from defaultValue. If the valid from JSON is
	 * invalid, an exception will be thrown.
	 *
	 * @param json         The JSON object
	 * @param propertyName The property to read from json
	 * @param defaultValue A default value to use if json does not have the given property
	 * @return A color parsed from the value read from json
	 * @throws NumberFormatException If the value from json cannot be parsed
	 */
	public static RGB from (JsonObject json, String propertyName, int defaultValue) {
		String defaultStr = Integer.toHexString(defaultValue);
		return parse(JsonUtils.getString(json, propertyName, defaultStr));
	}

	//endregion

	public RGB blendWith (RGB other) {
		return blend(this, other);
	}

	public static RGB blend (RGB color1, RGB color2) {
		return blend(color1, color2, 0.5f);
	}

	public static RGB blend (RGB color1, RGB color2, float ratio) {
		int i1 = color1.getColor();
		int i2 = color2.getColor();

		int color = blend(i1, i2, ratio);
		return new RGB(color);
	}

	public static int blend (int color1, int color2) {
		return blend(color1, color2, 0.5f);
	}

	public static int blend (int color1, int color2, float ratio) {
		ratio = MathHelper.clamp(ratio, 0f, 1f);
		float iRatio = 1f - ratio;

		int a1 = (color1 >> 24 & 0xff);
		int r1 = ((color1 & 0xff0000) >> 16);
		int g1 = ((color1 & 0xff00) >> 8);
		int b1 = (color1 & 0xff);

		int a2 = (color2 >> 24 & 0xff);
		int r2 = ((color2 & 0xff0000) >> 16);
		int g2 = ((color2 & 0xff00) >> 8);
		int b2 = (color2 & 0xff);

		int a = (int) ((a1 * iRatio) + (a2 * ratio));
		int r = (int) ((r1 * iRatio) + (r2 * ratio));
		int g = (int) ((g1 * iRatio) + (g2 * ratio));
		int b = (int) ((b1 * iRatio) + (b2 * ratio));

		return a << 24 | r << 16 | g << 8 | b;
	}

	public int getColor () {
		int r = (int) (red * 255f) << 16;
		int g = (int) (green * 255f) << 8;
		int b = (int) (blue * 255f);
		return r + g + b;
	}

	public int getColorWithA () {
		int a = (int) (alpha * 255f) << 24;
		int r = (int) (red * 255f) << 16;
		int g = (int) (green * 255f) << 8;
		int b = (int) (blue * 255f);
		return a + r + g + b;
	}

	public float getRed () {
		return red;
	}

	public float getGreen () {
		return green;
	}

	public float getBlue () {
		return blue;
	}

	public float getAlpha () {
		return alpha;
	}

	public int getRedInt () {
		return (int) (red * 255f);
	}

	public int getGreenInt () {
		return (int) (green * 255f);
	}

	public int getBlueInt () {
		return (int) (blue * 255f);
	}

	public int getAlphaInt () {

		return (int) (alpha * 255f);
	}
}
