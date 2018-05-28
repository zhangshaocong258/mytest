package com.szl;

/**
 * Created by zsc on 2018/4/23.
 * 枚举类型
 */
public class EnumTest {
    public static void main(String[] args) {
        for (Day day : Day.values()) {
            System.out.println(day.toString());
        }
        System.out.println(Day.MONDAY.ordinal());
    }
}

enum Day {
    MONDAY("星期一", 1),
    TUESDAY("星期二", 1),
    WEDNESDAY("星期三", 1),
    THURSDAY("星期四", 1),
    FRIDAY("星期五", 1),
    SATURDAY("星期六", 1),
    SUNDAY("星期七", 1);

    private String desc;
    private int index;

    Day(String desc, int index) {
        this.desc = desc;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Day: " + desc +
                ", index: " + index ;
    }
}
