package com.szl;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.SegmentationAlgorithm;
import org.apdplat.word.segmentation.Word;

import java.util.List;

/**
 * Created by zsc on 2016/12/10.
 */
public class WordTest {
    public static void main(String args[]) {
//        System.out.println(WordSegmenter.segWithStopWords("工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("他说的确实在理", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("别把手伸进别人的口袋里", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("老师说明天下午休息", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("新华社记者兰红光摄", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("这块地面积还真不小", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("在这些企业中国有企业有十个", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("原子结合成分子时", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("这样的人才能经受住考验", SegmentationAlgorithm.MaxNgramScore));
        System.out.println(WordSegmenter.segWithStopWords("在阿里巴巴当 HR 是怎样一种体验", SegmentationAlgorithm.MaxNgramScore));
    }

}
