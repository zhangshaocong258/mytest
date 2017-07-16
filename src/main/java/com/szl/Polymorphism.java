package com.szl;

/**
 * Created by zsc on 2016/10/20.
 * 多态 测试
 *
 */
public class Polymorphism {
    public static void main(String[] args) {
        Animal b = new Bird(); //向上转型
        b.eat();
        //  b.fly(); b虽指向子类对象，但此时子类作为向上的代价丢失和父类不同的fly()方法
        sleep(new Male());
        sleep(new Female());//传入的参数是子类-----！！
    }

    public static void sleep(Human h) //方法的参数是父类------！！！
    {
        h.sleep();
    }
}

class Animal {
    private String fun() {
        System.out.println("fun");
        return "fun";
    }
    public void eat() {//改成private报错
        System.out.println("父类的 eating...");
    }
}

class Bird extends Animal {
    public String fun() {
        return "1";
    }
    public void eat() {
        System.out.println("子类重写的父类的  eatting...");
    }

    public void fly() {
        System.out.println("子类新方法  flying...");
    }
}


class Human {
    public void sleep() {
        System.out.println("父类人类   sleep..");
    }
}

class Male extends Human {
    @Override
    public void sleep() {
        System.out.println("男人 sleep..");
    }
}

class Female extends Human {
    @Override
    public void sleep() {
        System.out.println("女人 sleep..");
    }
}