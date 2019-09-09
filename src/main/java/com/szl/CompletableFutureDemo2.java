package com.szl;

import java.util.concurrent.*;

/**
 * @author zhangshaocong
 * @date 2019-08-25
 */
public class CompletableFutureDemo2 {

    private static final ExecutorService ASYNC_CHECK_EXECUTOR = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws Exception{
        CompletableFuture<Result> future = null;
        future = CompletableFuture.supplyAsync(() -> asyncCheck());

        Thread.sleep(2500);

        Result defaultResult = new Result();
        defaultResult.setSuccess(false);
        if (future.getNow(defaultResult).isSuccess()) {
            System.out.println("succeed");
        } else {
            System.out.println("fail");
        }

    }

    private static Result asyncCheck() {
        Result result = null;
        try {
            Thread.sleep(2000);
//            result = new Result(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

class Result{

    private boolean success;

    public Result() {
    }

    public Result(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
