package com.szl;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.library.UserDefineLibrary;
import org.ansj.recognition.impl.FilterRecognition;
import org.ansj.splitWord.analysis.*;

import java.util.Collection;
import java.util.List;

/**
 * Created by zsc on 2016/12/8.
 */
public class AnsjTest {
    public static void main(String args[]) {
        KeyWordComputer kwc = new KeyWordComputer(10);
        String title = "在, 阿里, 巴巴, 当, HR, 是, 怎样, 一种, 体验";
        String content = "最近阿里巴巴的 HR 比较火，似乎还能掌握面试者的生杀大权。想问问在阿里巴巴当 HR，在招聘季和平时都做些什么工作，有什么体验";
        List<Keyword> result = kwc.computeArticleTfidf(title, content);
        System.out.println(result);
//        for (int i = 0; i < result.size(); i++) {
//            System.out.println(result.get(i).getName());
//        }
//        FilterRecognition filter = new FilterRecognition();
//        filter.insertStopNatures("u", "w"); //过滤词性
//        String words = "中国是世界四大文明古国之一，有着悠久的历史，距今约5000年前，以中原地区为中心开始出现聚落组织进而成国家和朝代，" +
//                "后历经多次演变和朝代更迭，持续时间较长的朝代有夏、商、周、汉、晋、唐、宋、元、明、清等。" +
//                "中原王朝历史上不断与北方游牧民族交往、征战，众多民族融合成为中华民族。20世纪初辛亥革命后，中国的君主政体退出历史舞台，取而代之的是共和政体。" +
//                "1949年中华人民共和国成立后，在中国大陆建立了人民代表大会制度的政体。中国有着多彩的民俗文化，传统艺术形式有诗词、戏曲、书法和国画等，" +
//                "春节、元宵、清明、端午、中秋、重阳等是中国重要的传统节日。";
//        System.out.println(ToAnalysis.parse(words));
//        System.out.println(ToAnalysis.parse(words).recognition(filter).toString(" "));
//        System.out.println(ToAnalysis.parse(words).recognition(filter).toStringWithOutNature("  "));
        System.out.println(DicAnalysis.parse("我国工人阶级和广大劳动群众要更加紧密地团结在党中央周围"));
        System.out.println(DicAnalysis.parse("别把手伸进别人的口袋里"));
        System.out.println(DicAnalysis.parse("房产的一次性交易流程"));
        System.out.println(ToAnalysis.parse("他说的确实在理"));
        System.out.println(DicAnalysis.parse("他说的确实在理"));
        System.out.println(ToAnalysis.parse("老师说明天下午休息"));
        System.out.println(DicAnalysis.parse("老师说明天下午休息"));
        System.out.println(ToAnalysis.parse("这块地面积还真不小"));
        System.out.println(DicAnalysis.parse("这块地面积还真不小"));
        System.out.println(ToAnalysis.parse("新华社记者兰红光摄"));
        System.out.println(DicAnalysis.parse("新华社记者兰红光摄"));
        System.out.println(ToAnalysis.parse("在这些企业中 国有企业 有十个"));
        System.out.println(DicAnalysis.parse("在这些企业中 国有企业 有十个"));
        System.out.println(ToAnalysis.parse("原子结合成分子时"));
        System.out.println(DicAnalysis.parse("原子结合成分子时"));
        System.out.println(ToAnalysis.parse("这样的人才能经受住考验"));
        System.out.println(DicAnalysis.parse("这样的人才能经受住考验"));
        System.out.println(IndexAnalysis.parse("明天下雨"));

//        UserDefineLibrary.insertWord();
    }
}

