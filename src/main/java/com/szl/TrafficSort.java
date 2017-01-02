package com.szl;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by zsc on 2016/8/17.
 * TrafficSort测试
 */
public class TrafficSort {
    ArrayList<TrafficKey> keys = new ArrayList<TrafficKey>();
    String path = "E:\\tttt\\10.0.1.2.txt";
    String outPath = "E:\\traffic\\10.0.1.2.txt";

    public static void main(String[] args) {
        TrafficSort trafficSort = new TrafficSort();
        trafficSort.sortTraffic(trafficSort.path, trafficSort.outPath);
    }

    public void sortTraffic(String path, String outPath) {
        try {
            InputStreamReader in = new InputStreamReader(new FileInputStream(path), "UTF-8");
            BufferedReader bin = new BufferedReader(in);
            String curLine;

            while ((curLine = bin.readLine()) != null) {
                String str[] = curLine.split(",");
                TrafficKey key = new TrafficKey();
                key.setTime(Long.valueOf(str[0]));
                key.setSrcIp(str[1]);
                key.setDstIp(str[2]);
                key.setProtocol(str[3]);
                keys.add(key);
            }
            bin.close();
            Collections.sort(keys);

            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(outPath), "UTF-8");
            BufferedWriter bout = new BufferedWriter(out);

            for (TrafficKey key : keys) {
                StringBuilder sb = new StringBuilder();
                sb.append(key.getTime()).append(",").append(key.getSrcIp()).append(",").
                        append(key.getDstIp()).append(",").append(key.getProtocol());
                bout.write(sb.toString());
                bout.newLine();
            }
            bout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
