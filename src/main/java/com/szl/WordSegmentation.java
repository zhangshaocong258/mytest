package com.szl;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by zsc on 2016/12/14.
 * 多个词库合为一个
 */
public class WordSegmentation {
    public static void main(String args[]) throws IOException {
        String path = "D:\\中文分词库";
        String outPath = "D:\\out.txt";
//        firstStep(path, "txt");//替换为空格
//        String path2 = "D:\\中文分词库\\上海地名new.txt";
//        secondStep(path2);//加上词性和词频
        WordSegmentation ws = new WordSegmentation();
        ws.lastStep(path, outPath, "txt");//最后一步，


    }

    private static void firstStep(String path, String type) throws IOException {
        File folder = new File(path);
        if (folder.isFile() && path.endsWith(type)) {
            genSpacing(folder);
        }
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile() && files[i].getAbsolutePath().endsWith(type)) {
                    genSpacing(files[i]);
                }
            }
        }
    }

    private static void genSpacing(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        List<String> lines2 = new ArrayList<String>();
        for (int i = 0; i < lines.size(); i++) {
            lines2.add(lines.get(i).replace(":", " "));
        }
        File file2 = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + "new" + ".txt");
        FileUtils.writeLines(file2, lines2);
    }

    private static void secondStep(String path2) throws IOException {
        File file = new File(path2);
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        List<String> lines2 = new ArrayList<String>();
        for (int i = 0; i < lines.size(); i++) {
            lines2.add(new StringBuilder().append(lines.get(i)).append(" ").append("ns").append(" ").append("1").toString());
        }
        File file2 = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(".")) + "new2" + ".txt");
        FileUtils.writeLines(file2, lines2);
    }

    private void lastStep(String path, String outPath, String type) throws IOException {
        Set<Word> hashSet = new HashSet<Word>();
        List<String> lines2 = new ArrayList<String>();
        File folder = new File(path);
        if (folder.isFile() && path.endsWith(type)) {
            combine(folder, hashSet);
        }
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile() && files[i].getAbsolutePath().endsWith(type)) {
                    if (files[i].getName().equals("jiebanew.txt")) {
                        combineTran(files[i], hashSet);
                    } else {
                        combine(files[i], hashSet);
                    }
                }
            }
        }
        File outFile = new File(outPath);
        for (Word word : hashSet) {
            lines2.add(new StringBuilder().append(word.getWord()).append(" ").
                    append(word.getProperty()).append(" ").append(word.getFrequency()).toString());
        }
        FileUtils.writeLines(outFile, lines2);
    }

    //添加进set，进行输出
    private void combine(File file, Set<Word> hashSet) throws IOException {
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        for (String str : lines) {
            hashSet.add(new Word(str.split(" ")[0],str.split(" ")[1],str.split(" ")[2]));
        }
    }

    //针对jieba分词，交换位置
    private void combineTran(File file, Set<Word> hashSet) throws IOException {
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        for (String str : lines) {
            hashSet.add(new Word(str.split(" ")[0],str.split(" ")[2],str.split(" ")[1]));
        }
    }

    class Word {
        private String word;
        private String property;
        private String frequency;

        Word(String word, String property, String frequency) {
            this.word = word;
            this.property = property;
            this.frequency = frequency;
        }

        public String getFrequency() {
            return frequency;
        }

        public String getProperty() {
            return property;
        }

        public String getWord() {
            return word;
        }

        @Override
        public int hashCode() {
            return this.getWord().hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Word w = (Word) obj;
            return this.getWord().equals(w.getWord());
        }

//        @Override
//        public int compareTo(Word o) {
//            if (word == o.word) {
//                return 0;
//            } else {
//                return word.compareTo(o.word);
//            }
//        }
    }
}
