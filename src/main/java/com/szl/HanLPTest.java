package com.szl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.dictionary.other.CharType;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.dictionary.stopword.Filter;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.suggest.Suggester;
import com.hankcs.hanlp.tokenizer.BasicTokenizer;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zsc on 2016/12/12.
 * CustomDictionary中添加新词时，需要删除下面的bin，其他的应该类似，间隔是空格
 * filter用于索引分词的去停用词，只能自己定义，没有api
 */
public class HanLPTest {
    public static void main(String args[]) {


        //过滤
        CoreStopWordDictionary.FILTER = new Filter()
        {
            @Override
            public boolean shouldInclude(Term term) {
                String nature = term.nature != null?term.nature.toString():"空";
                char firstChar = nature.charAt(0);
                switch (firstChar)
                {
                    case 'b': //区别词 正 副
                    case 'z': //状态词
                    case 'r': //代词 怎样 如何
                    case 'm':
                        return true;
                    case 'c':
                    case 'e':

                    case 'o':
                    case 'p':
                    case 'q':

                    case 'u':
                    case 'w':
                    case 'y':
                        return false;
                    case 'd':
                    case 'f':
                    case 'g':
                    case 'h':
                    case 'i':
                    case 'j':
                    case 'k':
                    case 'l':
                    case 'n':
                    case 's':
                    case 't':
                    case 'v':
                    case 'x':

                    default:
                        return term.word.length() > 1 && !CoreStopWordDictionary.contains(term.word);
                }
            }
        };

        System.out.println("\n***********标准歧义语句*************");
        System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
        System.out.println(StandardTokenizer.segment("这样的人才能经受住考验"));
        System.out.println(StandardTokenizer.segment("在这些企业中国有企业有十个"));
        System.out.println(StandardTokenizer.segment("原子结合成分子时"));
        System.out.println(StandardTokenizer.segment("明天下雨"));
        System.out.println(StandardTokenizer.segment("新华社记者兰红光摄"));
        System.out.println(StandardTokenizer.segment("这块地面积还真不小"));
        System.out.println(StandardTokenizer.segment("老师说明天下午休息"));
        System.out.println(StandardTokenizer.segment("他说的确实在理"));
        System.out.println(StandardTokenizer.segment("别把手伸进别人的口袋里"));
        System.out.println(StandardTokenizer.segment("房产的一次性交易流程"));
        System.out.println(StandardTokenizer.segment("在阿里巴巴当HR是怎样一种体验"));
        System.out.println(StandardTokenizer.segment("张飞-知乎"));
        System.out.println(StandardTokenizer.segment("不要不留后路"));
        System.out.println(StandardTokenizer.segment("郑州副局长要火"));
        System.out.println(StandardTokenizer.segment("飞行员年薪有百万吗？"));
        System.out.println(filter(StandardTokenizer.segment("如何才能成为一名优秀的营销策划人")));


        System.out.println("\n***********HLP歧义语句*************");
        System.out.println(HanLP.segment("这样的人才能经受住考验"));
        System.out.println(HanLP.segment("在这些企业中国有企业有十个"));
        System.out.println(HanLP.segment("原子结合成分子时"));
        System.out.println(HanLP.segment("明天下雨"));
        System.out.println(HanLP.segment("新华社记者兰红光摄"));
        System.out.println(HanLP.segment("这块地面积还真不小"));
        System.out.println(HanLP.segment("老师说明天下午休息"));
        System.out.println(HanLP.segment("他说的确实在理"));
        System.out.println(HanLP.segment("别把手伸进别人的口袋里"));
        System.out.println(HanLP.segment("房产的一次性交易流程"));
        System.out.println(HanLP.segment("在阿里巴巴 当 HR 是怎样一种体验"));
        System.out.println(HanLP.segment("严守一把手机关了"));
        System.out.println(HanLP.segment("吉林省长春药店"));
        System.out.println(HanLP.segment("乒乓球拍卖啦"));
        System.out.println(HanLP.segment("南京市长江大桥"));
        System.out.println(HanLP.segment("赵军坑秦兵四十万于长亭"));
        System.out.println(HanLP.segment("无论我打败了猪还是我打胜了猪基本上总在说明它没赢我没输"));
        System.out.println(HanLP.segment("台上坐着主席团"));
        System.out.println(HanLP.segment("小明在火车上画画"));
        System.out.println(HanLP.segment("江阴毛纺织厂"));
        System.out.println(HanLP.segment("北京大学生前来应聘"));
        System.out.println(HanLP.segment("结婚的和尚未结婚的"));
        System.out.println(HanLP.segment("这事的确定不下来"));
        System.out.println(HanLP.segment("有关刘晓庆偷税案"));
        System.out.println(HanLP.segment("大学生活动中心"));
        System.out.println(HanLP.segment("有意见分歧"));
        System.out.println(HanLP.segment("南京市长江大桥"));
        System.out.println(HanLP.segment("IBM（中国）是一家怎样的公司？"));
        System.out.println(HanLP.segment(("如何阅读上市公司的年报？有哪些较好的方法？")));
        System.out.println(filter(HanLP.segment(("飞行员年薪有百万吗？"))));
        System.out.println(filter(HanLP.segment(("在阿里工作三个月很想辞职怎么办？"))));
        System.out.println(HanLP.segment(("捕获知乎数据")));
        System.out.println(filter(HanLP.segment(("上知乎看小说"))));



//        CustomDictionary.insert("白富美", "nz 1024");
        System.out.println("\n***********索引语句*************");
        System.out.println(IndexTokenizer.segment("为何赵本山的徒弟比郭德纲的徒弟忠诚度要高？"));//要去掉问号
        System.out.println(IndexTokenizer.segment("《神奇动物在哪里》中有哪些不易察觉的彩蛋或细节？"));
        System.out.println(IndexTokenizer.segment("在阿里巴巴当hr是一种怎样的体验？"));
        System.out.println(IndexTokenizer.segment("郑州副局长要火"));
        System.out.println(filter(IndexTokenizer.segment("在阿里巴巴当hr是一种怎样的体验？")));
        System.out.println(filter(IndexTokenizer.segment("《神奇动物在哪里》中有哪些不易察觉的彩蛋或细节？？")));
        System.out.println(filter(IndexTokenizer.segment("副局长要火")));
        System.out.println(filter(IndexTokenizer.segment("张少聪-知乎")));
        System.out.println(filter(IndexTokenizer.segment("在阿里巴巴当hr是一种怎样的体验")));
        System.out.println(filter(IndexTokenizer.segment("如何阅读上市公司的年报？有哪些较好的方法？")));
        System.out.println(filter(IndexTokenizer.segment("飞行员年薪有百万吗？")));
        System.out.println(filter(IndexTokenizer.segment("在阿里工作三个月很想辞职怎么办？")));


        System.out.println("\n***********关键词语句*************");
        System.out.println(HanLP.extractKeyword("为何赵本山的徒弟比郭德纲的徒弟忠诚度要高", 20));
        System.out.println(HanLP.extractKeyword("郑州副局长要火", 20));
        System.out.println(HanLP.extractKeyword("在阿里巴巴当hr是一种怎样的体验", 20));
        System.out.println(HanLP.extractKeyword("《神奇动物在哪里》中有哪些不易察觉的彩蛋或细节", 20));
        System.out.println(HanLP.extractKeyword("哈利·波特如果当时进了斯莱特林学院会怎么样", 20));
        System.out.println(HanLP.extractKeyword(("如何阅读上市公司的年报？有哪些较好的方法？"), 20));
        System.out.println(HanLP.extractKeyword(("飞行员年薪有百万吗？"), 20));
        System.out.println(HanLP.extractKeyword(("在阿里工作三个月很想辞职怎么办？"), 20));


        System.out.println("\n***********过滤语句*************");
        System.out.println(NotionalTokenizer.segment("为何赵本山的徒弟比郭德纲的徒弟忠诚度要高"));
        System.out.println(NotionalTokenizer.segment("在阿里巴巴当hr是一种怎样的体验"));
        System.out.println(NotionalTokenizer.segment("《神奇动物在哪里》中有哪些不易察觉的彩蛋或细节"));
        System.out.println(NotionalTokenizer.segment("哈利·波特如果当时进了斯莱特林学院会怎么样"));
        System.out.println(NotionalTokenizer.segment("三个臭皮匠"));
        System.out.println(NotionalTokenizer.segment("怎样在大学宿舍学习"));
        System.out.println(NotionalTokenizer.segment("郑州副局长要火"));
        System.out.println(NotionalTokenizer.segment("在阿里巴巴当hr是一种怎样的体验"));
        System.out.println(NotionalTokenizer.segment("飞行员年薪有百万吗"));
        System.out.println(NotionalTokenizer.segment("上知乎看小说"));
        System.out.println(NotionalTokenizer.segment("在阿里工作三个月很想辞职怎么办"));

        System.out.println("\n***********语义距离*************");
//        synonym();
    }

    private static void synonym() {
        //可自定义，同义词距离为零
        String[] wordArray = new String[]
                {
                        "香蕉",
                        "苹果",
                        "白菜",
                        "水果",
                        "蔬菜",
                        "自行车",
                        "公交车",
                        "飞机",
                        "买",
                        "卖",
                        "购入",
                        "新年",
                        "春节",
                        "丢失",
                        "补办",
                        "办理",
                        "送给",
                        "寻找",
                        "孩子",
                        "教室",
                        "教师",
                        "会计",
                };
        for (String a : wordArray)
        {
            for (String b : wordArray)
            {
                System.out.println(a + "\t" + b + "\t之间的距离是\t" + CoreSynonymDictionary.distance(a, b));
            }
        }
    }

    private static List<Term> filter(List<Term> termList) {
        List<Term> temp = new ArrayList<Term>();
        for (int i = 0; i < termList.size(); i++) {
            Term term = termList.get(i);
            String nature = term.nature != null ? term.nature.toString() : "空";
            char firstChar = nature.charAt(0);
            switch(firstChar) {
                case 'b': //区别词 正 副
                case 'z': //状态词
                case 'r': //代词 怎样 如何
                case 'm':
                    break;
                case 'c':
                case 'e':

                case 'o':
                case 'p':
                case 'q':

                case 'u':
                case 'w':
                case 'y':
                    temp.add(term);
                    break;
                case 'd':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'n':
                case 's':
                case 't':
                case 'v':
                case 'x':
                default:
                    if(term.word.length() == 1) {//长度为1，删除，可以理解为没有分出来词，因此删除，最后查询时分出的词，也可以删除停用词
                        temp.add(term);
                    }
            }
        }
        termList.removeAll(temp);
        return termList;
    }

    private static String filter2(List<Term> termList) {
        List<Term> temp = new ArrayList<Term>();
        for (int i = 0; i < termList.size(); i++) {
            Term term = termList.get(i);
            String nature = term.nature != null ? term.nature.toString() : "空";
            char firstChar = nature.charAt(0);
            switch(firstChar) {
                case 'b': //区别词 正 副
                case 'z': //状态词
                case 'r': //代词 怎样 如何
                    break;
                case 'c':
                case 'e':
                case 'm':
                case 'o':
                case 'p':
                case 'q':

                case 'u':
                case 'w':
                case 'y':
                    temp.add(term);
                    break;
                case 'd':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'n':
                case 's':
                case 't':
                case 'v':
                case 'x':
                default:
                    if(term.word.length() == 1) {//长度为1，删除，可以理解为没有分出来词，因此删除，最后查询时分出的词，也可以删除停用词
                        temp.add(term);
                    }
            }
        }
        termList.removeAll(temp);
        return termList.toString();
    }
}
