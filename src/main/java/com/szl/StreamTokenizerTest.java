package com.szl;

import java.io.*;

/**
 * Created by zsc on 2016/7/27.
 */
public class StreamTokenizerTest {
    public static void main(String[] args) {

        try {
            String s;
            String path = "E:\\test.txt";
            InputStreamReader in = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader bin = new BufferedReader(in);
            StreamTokenizer st = new StreamTokenizer(bin);
//            st.ordinaryChar('.');
            st.ordinaryChar('-');
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                switch (st.ttype) {
                    case StreamTokenizer.TT_EOL:
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        //如果当前标记是一个数字，nval字段将包含该数字的值
                        s = String.valueOf((st.nval));
                        System.out.println("数字：" + s);
                        break;
                    //TT_WORD指示已读到一个文字标记的常量
                    case StreamTokenizer.TT_WORD:
                        //如果当前标记是一个文字标记，sval字段包含一个给出该文字标记的字符的字符串
                        s = st.sval;
                        System.out.println("文字：" + s);
                        break;
                    default:
                        //如果以上3中类型都不是，则为英文的标点符号
                        s = String.valueOf((char) st.ttype);
                        System.out.println("其他：" + s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
