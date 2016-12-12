package com.szl;

/**
 * Created by zsc on 2016/10/20.
 */
public class AbstractTest extends Parent {

    @Override
    void a() {

    }

    @Override
    void b() {

    }

    @Override
    void d() {

    }

}

abstract class Parent {
    abstract void a();
    abstract void b();
    abstract void d();

}

interface interfaceTest {
    static int TASK_ADD = 1;
    static int TASK_DEL = 2;
    static int TASK_MODIFY = 3;
    static int TASK_DISPLAY = 4;

    static int TASK_START = 5;
    static int TASK_STOP = 6;


    static int TASK_MODIFY_RestartMining = 1;	// 修改了MiningMethod
    static int TASK_MODIFY_ELSE = 2;



    public static final int eventType = 0;


    void onTaskAddedDis();

    default void onTaskAdded() {
        System.out.println("aa");
    }

    static void aa() {
        System.out.println("aaa");
    }

}
