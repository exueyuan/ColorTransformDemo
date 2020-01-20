package com.duyi.colorform;

import org.w3c.dom.Document;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomUtils {

    public static void changeColor(String soucePath) {
        /**
         * java以w3c.DOM方式读取xml文件
         */
        org.w3c.dom.Document doc = useDomReadXml(soucePath);
        //读取xml内部节点集合
        org.w3c.dom.NodeList nlst = doc.getElementsByTagName("color");
        //遍历集合内容
        for (int i = 0; i < (nlst).getLength(); i++) {
            String color = doc.getElementsByTagName("color").item(i).getFirstChild().getNodeValue();
            String replaceColor = MyClass.getTransformColorString(color);
            System.err.println("原颜色值:" + color + ",变了之后的颜色值：" + replaceColor);

            ReplaceUtils.replacTextContent(soucePath, color, replaceColor);
        }
    }

    /**
     * java读取xml文件四中方式之一
     * w3c.DOM方式实现
     * Red_ant
     * 20181105
     */
    public static Document useDomReadXml(String soucePath){
        File file = new File(soucePath);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            return doc;
        } catch (Exception e) {
            System.err.println("读取该xml文件失败");
            e.printStackTrace();
        }
        return null;
    }
}
