package com.szl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zsc on 2017/6/13.
 * 深拷贝
 */
public class DeepClone {
    public static void main(String args[]) throws Exception {
        ArrayList<PojoStr> listPojoStr0 = new ArrayList<>();
        PojoStr p1 = new PojoStr();
        p1.setStr("xxx1");
        listPojoStr0.add(p1);
        PojoStr p2 = new PojoStr();
        p2.setStr("xxx2");
        listPojoStr0.add(p2);
        PojoStr p3 = new PojoStr();
        p3.setStr("xxx3");
        listPojoStr0.add(p3);

        List<PojoStr> listPojoStr1 = new ArrayList<>(listPojoStr0);

        List<PojoStr> listPojoStr2 = new ArrayList<>();
        for (int i = 0, l = listPojoStr0.size(); i < l; i++)
            listPojoStr2.add(listPojoStr0.get(i));

        List<PojoStr> listPojoStr3 = new ArrayList<>(Arrays.asList(new PojoStr[listPojoStr0.size()]));
        Collections.copy(listPojoStr3, listPojoStr0);

        PojoStr[] strs = new PojoStr[listPojoStr0.size()];
        System.arraycopy(listPojoStr0.toArray(), 0, strs, 0, listPojoStr0.size());
        ArrayList<PojoStr> listPojoStr4 = (ArrayList<PojoStr>) listPojoStr0.clone();

        listPojoStr0.get(0).setStr("rock");

        for (int i = 0, l = listPojoStr0.size(); i < l; i++) {
            System.out.println("listPojoStr0的第" + i + "个值为：" + listPojoStr0.get(i).getStr());
            System.out.println("listPojoStr1的第" + i + "个值为：" + listPojoStr1.get(i).getStr());
            System.out.println("listPojoStr2的第" + i + "个值为：" + listPojoStr2.get(i).getStr());
            System.out.println("listPojoStr3的第" + i + "个值为：" + listPojoStr3.get(i).getStr());
            System.out.println("listPojoStr4的第" + i + "个值为：" + listPojoStr4.get(i).getStr());
            System.out.println("------------------------------------------------");
        }
    }
}

class PojoStr implements Serializable {
    /**
     *
     */
    @Override
    protected PojoStr clone() {
        PojoStr clone = null;
        try {
            clone = (PojoStr) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); // won't happen
        }

        return clone;
    }

    private static final long serialVersionUID = 4394836462951175834L;

    private String str = "";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

