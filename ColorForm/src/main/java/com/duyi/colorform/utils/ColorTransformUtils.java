package com.duyi.colorform.utils;

import com.duyi.colorform.ChangeLessColor;

import java.awt.Color;

public class ColorTransformUtils {
    public static String getTransformColorString(String color) {
        //这一步，先遍历所有可转换颜色值
        for (String originalColor : ChangeLessColor.translessHashMap.keySet()) {
            String upperChangeColor = getUpperCaseColorString(originalColor);
            String needChangeColor = getUpperCaseColorString(color);
            if (needChangeColor.equals(upperChangeColor)) {
                //如果两个颜色一样的话，那么直接返回转换后的颜色值就可以了
                return ChangeLessColor.translessHashMap.get(originalColor);
            }
        }

        //获得初始的颜色值
        Color startColor = fromStrToARGB(color);
        //根据初始的颜色值，返回转换后的颜色值
        Color endColor = getTransform(startColor);

        return "#"+colorToHexValue(endColor);
    }

    private static Color getTransform(Color newColor) {
        int startA = newColor.getAlpha();
        int startR = newColor.getRed();
        int startG = newColor.getGreen();
        int startB = newColor.getBlue();

        int endA = startA;
        int endR = 255 - startR;
        int endG = 255 - startG;
        int endB = 255 - startB;

        Color endColor = new Color(endR, endG, endB, endA);
        return endColor;
    }

    //16进制转color
    private static Color fromStrToARGB(String str) {
        str = getUpperCaseColorString(str);
        System.out.println("颜色值：" + str.toUpperCase());
        String str1 = str.substring(0, 2);
        String str2 = str.substring(2, 4);
        String str3 = str.substring(4, 6);
        String str4 = str.substring(6, 8);
        int alpha = Integer.parseInt(str1, 16);
        int red = Integer.parseInt(str2, 16);
        int green = Integer.parseInt(str3, 16);
        int blue = Integer.parseInt(str4, 16);
        Color color = new Color(red, green, blue, alpha);
        return color;
    }

    public static String getUpperCaseColorString(String str) {
        if (str.startsWith("#")) {
            str = str.substring(1);
        }
        if (str.length() == 6) {
            str = "FF" + str;
        } else if (str.length() == 4) {
            String strA = str.substring(0, 1);
            String strR = str.substring(1,2);
            String strG = str.substring(2, 3);
            String strB = str.substring(3, 4);
            str = strA + strA + strR + strR + strG + strG + strB + strB;
        } else if (str.length() == 3) {
            String strA = "FF";
            String strR = str.substring(0, 1);
            String strG = str.substring(1,2);
            String strB = str.substring(2, 3);
            str = strA + strA + strR + strR + strG + strG + strB + strB;
        }
        return str.toUpperCase();
    }

    private static String colorToHexValue(Color color) {
        return intToHexValue(color.getAlpha()) + intToHexValue(color.getRed()) + intToHexValue(color.getGreen()) + intToHexValue(color.getBlue());
    }

    private static String intToHexValue(int number) {
        String result = Integer.toHexString(number & 0xff);
        while (result.length() < 2) {
            result = "0" + result;
        }
        return result.toUpperCase();
    }
}
