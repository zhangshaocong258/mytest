package com.szl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.*;

/**
 * Created by zsc on 2017/6/25.
 * finallytest
 */
public class FinallyTest {
    public static void main(String args[]) {
        ExecutorService exec = Executors.newFixedThreadPool(3);
        ArrayList<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
        for (int i = 0; i < 10; i++) {
            FinalTemp temp = new FinalTemp();
            results.add(exec.submit(temp));
        }
//        temp.call();
        for (int i = 0; i < results.size(); i++) {
            try {
                results.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                exec.shutdown();
            }
        }


    }
}

/**
 * 当exec.submit(temp)时，必须取消注释才能抛出NullPointerException，而且必须是try/catch，
 * 若全部throws，则完全无法抛出NullPointerException，若是call，则try/catch或者throws都可以抛出NullPointerException，
 * 项目里最终是exec.submit，因此没有抛出异常，必须加上catch (NullPointerException e)
 */
class FinalTemp implements Callable {
    public Boolean call() {

        for (int i = 0; i < 5; i++) {
            System.out.println("i" + i);
            try {
                System.out.println(function());
            } catch (NullPointerException e) {
                System.out.println("cc");
                e.printStackTrace();
            }//当exec.submit(temp)时，必须取消注释才能抛出NullPointerException
        }

        return true;
    }


    public String function() {

        try {
            HashSet<String> ports = new HashSet<>();
            for (int i = 0; i < 10; i++) {
                ports.add(i + "");
            }
            String port = "11";
            if (ports.contains(port)) {
                System.out.println("contains");
            } else {
                System.out.println("no");
                throw new NullPointerException("不存在");
            }
            System.out.println("test");
        } finally {
            System.out.println("执行final");
            return "reeeee";
        }
    }
}