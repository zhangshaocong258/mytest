package com.szl;

import java.util.ArrayList;

/**
 * Created by zsc on 2016/3/11.
 * 查看hashcode
 */
public class HashCode {
    HashNum n = new HashNum();

    public static void main (String[] args){
        new HashCode().in();
        new Thread(new HashCode().new Server()).start();
    }
    void in(){
        System.out.println(n.s);
        System.out.println(n.s.hashCode());

        n.s.add("bb");
        System.out.println(n.s);
        System.out.println(n.s.hashCode());


    }
    class Server implements Runnable {
        public void run() {
            n.s.add("aaaaaa");
            System.out.println(n.s);
        }
        }
}

class HashNum{
    ArrayList<String> s = new ArrayList<String>();
    void init(){

    }
}

