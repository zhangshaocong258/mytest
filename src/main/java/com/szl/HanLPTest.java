package com.szl;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;

/**
 * Created by zsc on 2016/12/12.
 */
public class HanLPTest {
    public static void main(String args[]) {
//        System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
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
        System.out.println(StandardTokenizer.segment("在阿里巴巴当 HR 是怎样一种体验"));

        System.out.println("*******************************");
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
        System.out.println(HanLP.segment("薄熙来到重庆"));
        System.out.println(HanLP.segment("无论我打败了猪还是我打胜了猪基本上总在说明它没赢我没输"));
        System.out.println(HanLP.segment("台上坐着主席团"));
        System.out.println(HanLP.segment("小明在火车上画画"));
        System.out.println(HanLP.segment("江阴毛纺织厂"));
        System.out.println(HanLP.segment("北京大学生前来应聘"));
        System.out.println(HanLP.segment("结婚的和尚未结婚的"));
        System.out.println(HanLP.segment("这事的确定不下来"));


        System.out.println(IndexTokenizer.segment("在阿里巴巴 当 HR 是怎样一种体验"));
        System.out.println(HanLP.extractKeyword("在阿里巴巴当HR是怎样一种体验", 10));
    }
}
