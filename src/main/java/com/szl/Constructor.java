package com.szl;

import java.util.ArrayList;

/**
 * Created by zsc on 2016/3/14.
 * 传参数，改变，相当于是b.add
 */
public class Constructor {
    public static void main(String[] args){
        ArrayList<String> b = new ArrayList<>();
        b.add("b");
        Replace r = new Replace(b);
        r.adda();
        System.out.println(b);
    }
}

class Replace{
    ArrayList<String> a = new ArrayList<>();
    Replace(ArrayList<String> aa){
        this.a = aa;
    }
    void adda(){
        a.add("haha");
    }
}


