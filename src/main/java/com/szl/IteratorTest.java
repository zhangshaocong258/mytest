package com.szl;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zsc on 2017/7/13.
 * map list 迭代使用iterator 并且使用iterator.remove()才不会报错
 * 否则 put remove都会报错
 */
public class IteratorTest {
    public static void main(String args[]) throws FileNotFoundException {

        Map<Long, String> mReqPacket = new HashMap<Long, String>();
        for (long i = 0; i < 15; i++) {
            mReqPacket.put(i, i + "");
        }

        for (Iterator<Map.Entry<Long, String>> iterator = mReqPacket.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<Long, String> entry = iterator.next();
            long key = entry.getKey();
            if (key < 10) {
//                iterator.remove();//不会报错
                mReqPacket.remove(key);//会报错
            }
        }

        for (Map.Entry<Long, String> entry : mReqPacket.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
