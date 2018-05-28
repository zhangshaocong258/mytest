package com.szl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zsc on 2018/4/15.
 * 泛型
 */
public class GenericTest {
    public static void main(String[] args) {
//        MyNode mn = new MyNode(5);
//        Node n = mn;
//        n.setData("Hello");
//        Integer x = mn.data;

        MyNode mn = new MyNode(5);
        PNode<Integer> n = mn;
        n.setData(1);
        Integer x = mn.data;
        System.out.println(x);



    }
}

class PNode<T> {

    public T data;

    public PNode(T data) {
        this.data = data;
    }

    public void setData(T data) {
        System.out.println("Node.setData");
        this.data = data;
    }
}

class MyNode extends PNode<Integer> {
    public MyNode(Integer data) {
        super(data);
    }

    public void setData(Integer data) {
        System.out.println("MyNode.setData");
        super.setData(data);
    }
}
