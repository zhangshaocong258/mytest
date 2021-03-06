package com.szl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * Created by zsc on 2016/12/2.
 * 删除script
 * StringWriter实现文件流转为字符串
 */
public class JsoupTest {
    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\www.zhihu.com_question_27424671.html");//地址路径，相对是src同级路径
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
        //返回转换结果
        if (writer != null) {
            String html = writer.toString();
            String baseUrl = "https://www.zhihu.com";
            Document doc = Jsoup.parse(html, baseUrl);
//            Elements links = document.select("script");
//            for (int i = 0; i < links.size(); i++) {
//                if (links.get(i).attr("data-reactid").equals("21")) {
//                    links.get(i).remove();
//                }
//                links.get(i).removeAttr("src");
//                System.out.println("script: " + links.get(i).toString());
//                System.out.println("script: " + links.get(i).attr("src"));
            //www.zhihu.com_question_27424671.html 当div中class有多个时select多次
            System.out.println(doc.select("div.zm-editable-content").select("div.clearfix").text());
            String description = doc.select("div.zm-item-vote-info").first().attr("data-votecount") + ",," +
                    doc.select("div.zm-item-answer-author-info").first().select("a.author-link").text() + ",," +
                    doc.select("div.RichContent-inner").first().text().substring(0, doc.select("div.RichContent-inner").first().text().length()
                            > 300 ? 300 : doc.select("div.RichContent-inner").first().text().length()) + "...";
        }
    }
}
