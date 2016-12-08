package com.szl;

import java.util.*;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by zsc on 2016/7/18.
 */
public class ListSortTest {

    public static void main(String[] args) {
        ArrayList<PcapNode> list = new ArrayList<PcapNode>();
        PcapNode p1 = new PcapNode(1, "1", 1);
        PcapNode p2 = new PcapNode(1, "1", 1);
        PcapNode p3 = new PcapNode(1, "1", 1);
        PcapNode p4 = new PcapNode(4, "1", 1);
        PcapNode p5 = new PcapNode(5, "1", 1);
        PcapNode p6 = new PcapNode(6, "1", 1);
        list.add(p1);
        list.add(p3);
        list.add(p2);
        list.add(p6);
        list.add(p5);
        list.add(p4);
        Collections.sort(list);
        for (PcapNode node : list) {
            System.out.println("treeset: " + node.getTime_s() + node.getFileName() + node.getTraffic());
        }
    }
}

class PcapNode implements Comparable {
    private String ID;//pcap节点（例：13）
    private long time_s;//时间戳（秒）
    private String fileName;//pcap文件名（例：13-0）
    private int traffic;//流量

    public PcapNode(long time_s, String fileName, int traffic) {
        this.time_s = time_s;
        this.fileName = fileName;
        this.traffic = traffic;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getTime_s() {
        return time_s;
    }

    public void setTime_s(long time_s) {
        this.time_s = time_s;
    }

    public int getTraffic() {
        return traffic;
    }

    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }

    @Override
    public int compareTo(Object arg0) {
        PcapNode node = (PcapNode) arg0;
        String str = time_s + "_" + fileName + "_" + traffic;
        String str2 = node.time_s + "_" + node.fileName + "_" + node.traffic;
        if (str.equals(str2)) {
            if (time_s == node.getTime_s()) {
                if (fileName == node.getFileName())
                    return 0;
                else {
                    return fileName.compareTo(node.getFileName());
                }
            } else {
                return time_s < node.getTime_s() ? -1 : 1;
            }
        } else {
            return str.compareTo(str2);
        }
    }
}
