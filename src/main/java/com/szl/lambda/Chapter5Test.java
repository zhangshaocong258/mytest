package com.szl.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * @author zhangshaocong
 * @date 2019-07-21
 */
public class Chapter5Test {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        /** 找出2011年的所有交易并按交易额排序(从低到高) */
        List<Transaction> result1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(transaction -> transaction.getValue()))
                .collect(Collectors.toList());
        System.out.println("找出2011年的所有交易并按交易额排序(从低到高)");
        System.out.println(result1);
        System.out.println(transactions);
        System.out.println();

        /** 交易员都在哪些不同的城市工作过 */
        List<String> result2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("交易员都在哪些不同的城市工作过");
        System.out.println(result2);
        System.out.println();

        /** 查找所有来自于剑桥的交易员，并按姓名排序 */
        List<Trader> result3 = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(trader -> trader.getName()))
                .collect(Collectors.toList());
        System.out.println("查找所有来自于剑桥的交易员，并按姓名排序");
        System.out.println(result3);
        System.out.println();

        /** 返回所有交易员的姓名字符串，按字母顺序排序 */
        String result4 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println("返回所有交易员的姓名字符串，按字母顺序排序");
        System.out.println(result4);
        System.out.println();

        /** 有没有交易员是在米兰工作的 */
        List<Trader> result5 = transactions.stream()
                .map(transaction -> transaction.getTrader())
                .filter(trader -> trader.getCity().equals("Milan"))
                .collect(Collectors.toList());
        System.out.println("有没有交易员是在米兰工作的");
        System.out.println(result5);
        System.out.println();

        /** 打印生活在剑桥的交易员的所有交易额 */
        System.out.println("打印生活在剑桥的交易员的所有交易额");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .forEach(System.out::println);
        System.out.println();


        /** 所有交易中，最高的交易额是多少 */
        Optional<Transaction> result6 = transactions.stream()
                .max(comparing(transaction -> transaction.getValue()));

        System.out.println("所有交易中，最高的交易额是多少");
        System.out.println(result6.get().getValue());
        System.out.println();


    }


}
