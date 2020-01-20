package com.duyi.colorform;

import org.w3c.dom.Document;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class MyClass {
    public static void main(String[] args) {

        String startColorString = "#FFb400";

        String transformColor = getTransformColorString(startColorString);
        System.out.println("startColor:" + startColorString);
        System.out.println("transformColor:" + transformColor);


        copyFileUsingJava7Files("ColorForm/libs/colors.xml", "ColorForm/libs/trans_colors.xml");
        DomUtils.changeColor("ColorForm/libs/trans_colors.xml");
    }

    private static void copyFileUsingJava7Files(String source, String dest) {
        InputStream input = null;
        OutputStream output = null;
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (Exception e) {
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getTransformColorString(String color) {
        //        Color startColor = new Color(255, 180, 0, 58);
        Color startColor = fromStrToARGB(color);
//        System.out.println("startColor:"+colorToHexValue(startColor));

        Color endColor = getTransform(startColor);

//        System.out.println("endColor:"+colorToHexValue(endColor));
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
        if (str.startsWith("#")) {
            str = str.substring(1);
        }
        if (str.length() == 6) {
            str = "FF" + str;
        }
        if (str.length() == 4) {
            str = "FF00" + str;
        }
        System.out.println("颜色值：" + str);
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
