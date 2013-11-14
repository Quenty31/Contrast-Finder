/*
 * Contrast Finder
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */ 

package org.opens.utils.colorconvertor;

import java.awt.Color;
import org.apache.log4j.Logger;

/**
 *
 * @author alingua
 */
public final class ColorConverter {

    private static final int MAX_COMPONENT = 3;
    private static final int BRIGHTNESS = 2;
    private static final int SATURATION = 1;
    private static final int HUE = 0;
    private static final int R_BEGIN_COLOR = 0;
    private static final int G_BEGIN_COLOR = 2;
    private static final int B_BEGIN_COLOR = 4;
    private static final int RGB_HEXA_LENGTH = 6;
    private static final int CONVERT_TO_BASE_16 = 16;
    private static final String HEXADECIMAL_DICTIONNARY = "[0-9A-Fa-f]+";

    public static String Rgb2hex(String backgroundColor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Private constructor, utility class
     */
    private ColorConverter() {
    }

    /**
     *
     * @param bgColor
     * @param offsetHue
     * @param offsetSaturation
     * @param offsetBrightness
     * @return
     */
    public static Color offsetHsbColor(Color bgColor, float offsetHue, float offsetSaturation, float offsetBrightness) {
        Logger.getLogger(ColorConverter.class).debug("Color to modify" + bgColor.hashCode());
        float[] hsbValues = new float[MAX_COMPONENT];
        Float hue, saturation, brightness;

        Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), hsbValues);

        // OFFSETING RSB VALUES
        hue = hsbValues[HUE] + offsetHue;
        saturation = hsbValues[SATURATION] + offsetSaturation;
        brightness = hsbValues[BRIGHTNESS] + offsetBrightness;
        Color color = Color.getHSBColor(hue, saturation, brightness);
        Logger.getLogger(ColorConverter.class).debug("converted Color " + color.hashCode());
        return color;
    }

    public static Float getBrightness(Color bgColor) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float brightness;
        Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), hsbValues);
        brightness = hsbValues[BRIGHTNESS];
        return brightness;
    }

    public static Float getSaturation(Color bgColor) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float saturation;
        Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), hsbValues);
        saturation = hsbValues[SATURATION];
        return saturation;
    }

    public static Float getHue(Color bgColor) {
        float[] hsbValues = new float[MAX_COMPONENT];
        Float hue;
        Color.RGBtoHSB(bgColor.getRed(), bgColor.getGreen(), bgColor.getBlue(), hsbValues);
        hue = hsbValues[HUE];
        return hue;
    }

    /**
     *
     * @param bgColor
     * @param offsetRed
     * @param offsetGreen
     * @param offsetBlue
     * @return
     */
    public static Color offsetRgbColor(Color bgColor, int offsetRed, int offsetGreen, int offsetBlue) {
        return new Color(bgColor.getRed() + offsetRed, bgColor.getGreen() + offsetGreen, bgColor.getBlue() + offsetBlue);
    }

    /**
     *
     * @param colorStr
     * @return the RGB Color from hex Color
     */
    public static Color hex2Rgb(String colorStr) {
        String str = colorStr.substring(1);
        if (str.matches(HEXADECIMAL_DICTIONNARY)
                && str.length() == RGB_HEXA_LENGTH
                && colorStr.charAt(0) == '#') {
            return new Color(
                    Integer.valueOf(str.substring(R_BEGIN_COLOR, G_BEGIN_COLOR), CONVERT_TO_BASE_16),
                    Integer.valueOf(str.substring(G_BEGIN_COLOR, B_BEGIN_COLOR), CONVERT_TO_BASE_16),
                    Integer.valueOf(str.substring(B_BEGIN_COLOR, RGB_HEXA_LENGTH), CONVERT_TO_BASE_16));
        }
        return null;
    }

    public static String hex2Rgb(Color color) {
        Integer red = color.getRed();
        Integer green = color.getGreen();
        Integer blue = color.getBlue();
        return ("rgb(" + red.toString() + ", " + green.toString() + ", " + blue.toString() + ")");
    }
    
    public static String Rgb2hex(Color color) {
        return (String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue())).toUpperCase();
    }
}