package com.szl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * Created by zsc on 2016/12/3.
 *
 * StringWriter实现文件流转为字符串
 * 文件底部添加信息标签
 */
public class JsoupGetContent {
    public static void main(String[] args) {
//        File file = new File("src\\main\\resources\\www.zhihu.com_question_27424671.html");//地址路径，相对是src同级路径
        File file = new File("src\\main\\resources\\www.zhihu.com_question_52562919_answer_131463456.html");//地址路径，相对是src同级路径
        InputStreamReader reader = null;
        StringWriter writer = new StringWriter();
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            //将输入流写入输出流
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        if (writer != null) {
            String html = writer.toString();
            Document document = Jsoup.parse(html);
            Element url = document.select("url").last();
            System.out.println(url.text());
            Element date = document.select("date").last();
            System.out.println(date.text());
            Element IP = document.select("IP").last();
            System.out.println(IP.text());
            Element length = document.select("length").last();
            System.out.println(length.text());
            Element tittle = document.select("title").first();
            System.out.println(tittle.text());
            Element content = document.select("div[class=zm-editable-content]").first();
            System.out.println(content.text().length());
        }
    }
}
