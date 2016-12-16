package com.szl;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zsc on 2016/12/16.
 * 删除多余的网页，正则表达式基础
 */
public class FilterURL {
    public static void main(String args[]) throws IOException {
        String path = "D:\\SpiderDownload\\zhihu-2016-12-11-22-48-52";
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (!formatDoc(f.getName())) {
                f.delete();
            }
        }
    }

    private static boolean formatDoc(String url) {
        boolean p = false;
        boolean o = false;
//        Pattern main = Pattern.compile("^(https://www.zhihu.com/)");
        Pattern question = Pattern.compile("^(www.zhihu.com_question_([0-9]+\\.html$))");
        Pattern people = Pattern.compile("^(www.zhihu.com_people_(.*\\.html))");
        Pattern collection = Pattern.compile("^(www.zhihu.com_collection_([0-9]+\\.html$))");
        Pattern topic = Pattern.compile("^(www.zhihu.com_topic_([0-9]+$\\.html))");
        Pattern roundTable = Pattern.compile("^(www.zhihu.com_roundtable_(.*\\.html))");
        Pattern org = Pattern.compile("^(www.zhihu.com_org_(.*\\.html))");
        Pattern publications = Pattern.compile("^(www.zhihu.com_publications_(.*\\.html))");

        Matcher questionMatcher = question.matcher(url);
        Matcher peopleMatcher = people.matcher(url);
        Matcher collectionMatcher = collection.matcher(url);
        Matcher topicMatcher = topic.matcher(url);
        Matcher roundTableMatcher = roundTable.matcher(url);
        Matcher orgMatcher = org.matcher(url);
        Matcher publicationsMatcher = publications.matcher(url);


        //正则不好判断，分两次判断，判断后面有没有/
        if (peopleMatcher.matches()) {
            if (!url.substring(21).contains("_")) {
                p = true;
            }
        }

        if (orgMatcher.matches()) {
            if (!url.substring(18).contains("_")) {
                o = true;
            }
        }

        if ((questionMatcher.matches() || p || collectionMatcher.matches()
                || topicMatcher.matches() || roundTableMatcher.matches() || o || publicationsMatcher.matches()) ) {
            return true;
        }
        else {
            return false;
        }
    }
}
