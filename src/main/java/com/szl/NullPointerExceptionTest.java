package com.szl;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zsc on 2016/12/7.
 * 不会出现NullPointerException("不存在")，因为 exec.submit(temp)，没有得到NullPointerException
 */
public class NullPointerExceptionTest {
    public static void main(String args[]) {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        Temp temp = new Temp();
        exec.submit(temp);
//        temp.call();


    }
}

/**
 * 当exec.submit(temp)时，必须取消注释才能抛出NullPointerException，而且必须是try/catch，
 * 若全部throws，则完全无法抛出NullPointerException，若是call，则try/catch或者throws都可以抛出NullPointerException，
 * 项目里最终是exec.submit，因此没有抛出异常，必须加上catch (NullPointerException e)
 */
class Temp implements Callable {
    public Boolean call()  {
        try {
            function2();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (NullPointerException e) {
//            e.printStackTrace();
//        }//当exec.submit(temp)时，必须取消注释才能抛出NullPointerException
        return true;
    }

    public void function2() throws IOException {
        File file = new File("E:\\test\\1.txt");
        InputStreamReader in = new InputStreamReader(new FileInputStream(file));
        in.close();
        function();
        System.out.println("test2");
    }

    public void function() {

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
    }
}
