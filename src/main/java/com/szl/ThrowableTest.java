package com.szl;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by zsc on 2018/4/10.
 * throw Exception
 */
public class ThrowableTest {
    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(new MyRunnable());
        thread.start();
        thread.join();
        ExecutorService exec = Executors.newFixedThreadPool(1);
        ArrayList<Future<Boolean>> results = new ArrayList<Future<Boolean>>();
        MyCallable myCallable = new MyCallable();
        results.add(exec.submit(myCallable));

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

class MyCallable implements Callable {
    private void throwException() {
        throw new NullPointerException("callable错误");
    }

    @Override
    public Object call() throws Exception {
        try {
            throwException();
        } catch (NullPointerException e) {//一定要catch，否则无法显示，因为 exec.submit(temp)，没有得到NullPointerException
            System.out.println("call catch");
            e.printStackTrace();
        } finally {
            return true;
        }
    }
}

class MyRunnable implements Runnable {

    private void throwException() {
        throw new NullPointerException("runnable错误");
    }
    @Override
    public void run() {
        try {
            throwException();
        } catch (NullPointerException e) {
            System.out.println("run catch");
            e.printStackTrace();
        }
    }
}
