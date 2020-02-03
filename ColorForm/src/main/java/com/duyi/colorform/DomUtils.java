package com.duyi.colorform;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DomUtils {

    public static void changeColor(String soucePath) {



        HashMap<String, Boolean> map = new HashMap<>();
        /**
         * java以w3c.DOM方式读取xml文件
         */
        org.w3c.dom.Document doc = useDomReadXml(soucePath);
        //读取xml内部节点集合
        org.w3c.dom.NodeList nlst = doc.getElementsByTagName("color");
        //遍历集合内容
        for (int i = 0; i < (nlst).getLength(); i++) {
            Element ele = (Element) nlst.item(i);
            String color = ele.getFirstChild().getNodeValue();
            String replaceColor = ColorTransformUtils.getTransformColorString(color);
            ele.getFirstChild().setNodeValue(replaceColor);
//            String colorString = MyClass.getUpperCaseColorString(color);
            /*System.err.println("原颜色值:" + color + ",变了之后的颜色值：" + replaceColor);
            if (map.get(color) == null) {
                map.put(color, true);
                ReplaceUtils.replacTextContent(soucePath, color, replaceColor);
            }*/
        }

        saveDom(doc, soucePath);
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

    public static void saveDom(Document doc, String soucePath) {
        try {
            //保存xml文件
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            //设置编码类型
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            StreamResult result = new StreamResult(new FileOutputStream(soucePath));
            //把DOM树转换为xml文件
            transformer.transform(domSource, result);
        }catch (Exception ex) {

        }

    }
}
