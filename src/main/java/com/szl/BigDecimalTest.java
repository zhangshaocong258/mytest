package com.szl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangshaocong
 * @date 2019-09-09
 */
public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal("11.233");
        BigDecimal bigDecimal2 = new BigDecimal("11.234");
        BigDecimal bigDecimal3 = new BigDecimal("11.235");
        BigDecimal bigDecimal4 = new BigDecimal("11.236");
        BigDecimal bigDecimal5 = new BigDecimal("11.237");
        BigDecimal bigDecimal6 = new BigDecimal("11.244");
        BigDecimal bigDecimal7 = new BigDecimal("11.245");
        BigDecimal bigDecimal8 = new BigDecimal("11.246");

        List<BigDecimal> list = new ArrayList<BigDecimal>(){{
            add(bigDecimal1);
            add(bigDecimal2);
            add(bigDecimal3);
            add(bigDecimal4);
            add(bigDecimal5);
            add(bigDecimal6);
            add(bigDecimal7);
            add(bigDecimal8);
        }};

        System.out.println("*****ROUND_UP***********");
        list.stream().forEach(bigDecimal -> System.out.println(bigDecimal +": " + bigDecimal.setScale(2,BigDecimal.ROUND_UP)));
        System.out.println();

        System.out.println("*****ROUND_DOWN***********");
        list.stream().forEach(bigDecimal -> System.out.println(bigDecimal +": " + bigDecimal.setScale(2,BigDecimal.ROUND_DOWN)));
        System.out.println();

        System.out.println("*****ROUND_CEILING***********");
        list.stream().forEach(bigDecimal -> System.out.println(bigDecimal +": " + bigDecimal.setScale(2,BigDecimal.ROUND_CEILING)));
        System.out.println();

        System.out.println("*****ROUND_FLOOR***********");
        list.stream().forEach(bigDecimal -> System.out.println(bigDecimal +": " + bigDecimal.setScale(2,BigDecimal.ROUND_FLOOR)));
        System.out.println();

        System.out.println("*****ROUND_HALF_UP***********");
        list.stream().forEach(bigDecimal -> System.out.println(bigDecimal +": " + bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP)));
        System.out.println();

        System.out.println("*****ROUND_HALF_DOWN***********");
        list.stream().forEach(bigDecimal -> System.out.println(bigDecimal +": " + bigDecimal.setScale(2,BigDecimal.ROUND_HALF_DOWN)));
        System.out.println();

        System.out.println("*****ROUND_HALF_EVEN***********");
        list.stream().forEach(bigDecimal -> System.out.println(bigDecimal +": " + bigDecimal.setScale(2,BigDecimal.ROUND_HALF_EVEN)));
        System.out.println();


    }
}
