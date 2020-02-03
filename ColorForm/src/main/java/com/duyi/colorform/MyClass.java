package com.duyi.colorform;


public class MyClass {
    public static void main(String[] args) {

        String startColorString = "#FFb400";

        String transformColor = ColorTransformUtils.getTransformColorString(startColorString);
        System.out.println("startColor:" + startColorString);
        System.out.println("transformColor:" + transformColor);


        FileUtils.copyFileUsingJava7Files("ColorForm/libs/colors.xml", "ColorForm/libs/transform/colors.xml");
        DomUtils.changeColor("ColorForm/libs/transform/colors.xml");
    }



}
