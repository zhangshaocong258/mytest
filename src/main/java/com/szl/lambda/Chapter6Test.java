package com.szl.lambda;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.*;

/**
 * @author zhangshaocong
 * @date 2019-07-23
 */
public class Chapter6Test {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );

        String shortMenu = menu.stream().map(dish -> dish.getName()).collect(joining());
        System.out.println(shortMenu);
    }
}
