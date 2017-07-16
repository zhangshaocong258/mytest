package com.szl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsc on 2016/6/5.
 * 参数赋一个新的值，不改变原值，调用方法可以发生改变
 */
public class ParameterTransferTest {

    public static void main(String[] args) {
        String str = "1234";
        changeStr(str);
        System.out.println(str);

        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "a");
        changeMap(map);
        System.out.println(map.size());

    }

    public static void changeStr(String str) {
        str = "welcome";
    }

    public static void changeMap(Map map) {
        map.put("2", "b");
    }
}
