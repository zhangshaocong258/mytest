package com.szl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class tttt {

    public static void main(String args[]) throws  IOException{
        File folder = new File("D:\\57data");
        File[] files = folder.listFiles();
        for (File file : files) {
            System.out.println(file.getName());
        }

        System.out.println(folder.isDirectory());
        System.out.println(folder.getName());
    }



//    //生成文件夹
//    public static void main(String[] args) throws Exception {
//        String path = "E:\\zsc";
//        for (int i = 0; i < 54; i++) {
//            File f = new File(path + File.separator + i);
//            f.mkdirs();
//        }
//    }



//    public static void main(String[] args) {
//        TreeSet<NodeAndTTL> ttlList = new TreeSet<NodeAndTTL>();
//        ttlList.add(new NodeAndTTL("52-52-1", 2));
//        ttlList.add(new NodeAndTTL("49-49-3", 2));
////        ttlList.add(new NodeAndTTL("49-49-4", 3));
////        ttlList.add(new NodeAndTTL("53-53-1", 3));
//        System.out.println(ttlList.size());
//        System.out.println("52-52-1".equals("49-49-3"));
//        for (NodeAndTTL nodeAndTTL : ttlList) {
//            System.out.println(nodeAndTTL.node.hashCode());
//        }
//
//
//    }

}
class NodeAndTTL implements Comparable<NodeAndTTL> {
    String node;
    Integer TTL;

    NodeAndTTL(String node, Integer TTL) {
        this.node = node;
        this.TTL = TTL;
    }

    @Override
    public int compareTo(NodeAndTTL arg0) {
        if (node == arg0.node) {
            if (TTL == arg0.TTL) {
                return 0;
            } else {
                return TTL < arg0.TTL ? -1 : 1;
            }
        } else {
            return node.compareTo(arg0.node);
        }
    }

//    @Override
//    public boolean equals(Object arg0) {
//        NodeAndTTL nodeAndTTL = (NodeAndTTL) arg0;
//
//        return this.node.equals(nodeAndTTL.node);
//    }
//
//    @Override
//    public int hashCode() {
//        return this.node.hashCode();
//    }
}








