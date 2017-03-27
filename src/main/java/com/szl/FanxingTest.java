package com.szl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsc on 2017/3/27.
 *
 * 泛型强制转换问题
 */
public class FanxingTest extends parent{
    List<FanxingTest> list = new ArrayList<FanxingTest>();
//    List<parent> parents = list;//报错
    List<? extends parent> parents = list;//不报错，List<? extends FanxingTest>也可以
}

class parent{
    public void p(){}
}
