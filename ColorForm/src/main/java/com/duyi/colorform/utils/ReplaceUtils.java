package com.duyi.colorform.utils;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReplaceUtils {

    /**
     * 替换文本文件中的 非法字符串
     *
     * @param path
     * @throws IOException
     */
    public static void replacTextContent(String path, String srcStr, String replaceStr) {
        try {
            // 读
            File file = new File(path);
            FileReader in = null;

            in = new FileReader(file);

            BufferedReader bufIn = new BufferedReader(in);
            // 内存流, 作为临时流
            CharArrayWriter tempStream = new CharArrayWriter();
            // 替换
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                // 替换每行中, 符合条件的字符串
                line = line.replaceAll(srcStr, replaceStr);
                // 将该行写入内存
                tempStream.write(line);
                // 添加换行符
                tempStream.append(System.getProperty("line.separator"));
            }
            // 关闭 输入流
            bufIn.close();
            // 将内存中的流 写入 文件
            FileWriter out = new FileWriter(file);
            tempStream.writeTo(out);
            out.close();
            System.out.println("====path:" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
