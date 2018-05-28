package com.szl;

/**
 * Created by zsc on 2017/9/15.
 * http://blog.csdn.net/iamluole/article/details/7977259
 * 重写实例方法：超类Parent中有实例方法A，子类child定义了与A“相同签名和子集返回类型”的实例方法B，子类对象ChildObj只能调用自己的实例方法B。
 * 即使将子类对象ChildObj转换为超类对象ParentObj，ParentObj依然只能调用重写后的实例方法B！(超类对象ParentObj中的实例方法A已经被实例方法B覆盖了)
 *
 *
 * 只有成员变量(不管是不是静态)和静态方法可以被隐藏。
 *
 *
 * 重写的语法规则如下：
 * (1)方法签名必须相同(参数类型、个数、顺序);
 * (2)对返回类型有要求，分2种情况：
 *  a.被重写方法的返回类型是基本类型：重写方法的返回类型必须“相同”。
 * 基本类型包括了(byte,short,int,long,float,double,char,boolean，其实还包括一个void类型)，但要注意返回类型是封装类时属于下面的情况b。
 *  b.被重写方法的返回类型是引用类型：重写方法的返回类型应“相同”或是其“子类型”；
 * 引用类型包括了数组、string等一切非基本类型的类型(即类类型)。
 * (3)重写方法的访问权限不能小于被重写方法的访问权限，可以更广泛。如被重写方法是包访问权限，重写方法是public访问权限。
 * 重写方法可以改变其它的方法修饰符，如final、synchronized、native、strictfp。
 * 不管被重写方法中有无final修饰的参数，重写方法都可以增加、保留、去掉这个参数的final修饰符(参数修饰符不属于方法签名)。
 * (4)重写方法抛出的异常范围不能大于被重写方法抛出的异常的范围（也可以不抛出异常）。
 * (5)不能重写final方法。(final修饰符存在的意义就是防止任何子类重写该方法)
 * (6)不能重写static静态方法。(形式上可以写，但本质上不是重写，属于下面要讲的隐藏)
 * (7)如果一个方法不能被继承，则不能重写它。或者说：只有当方法可以被访问时才可以被重写。
 * 典型的就是超类的private方法。
 * 对于(3)需要注意：
 * 1.重写override对返回类型有要求，而重载overload对返回类型没有要求。
 * 重载可以改变返回类型。因为编译器通过对方法签名的识别即可静态编译出不同的方法。这也是java中重载与重写的区别之一。
 * 2.严格来说，重写属于多态，因为是动态绑定（或称为动态联编），而重载是静态绑定，编译时即可确定。
 *
 *
 *
 * 隐藏成员变量：超类Parent中有成员变量A，子类Child定义了与A同名的成员变量B，子类对象ChildObj调用的是自己的成员变量B。
 * 如果把子类对象ChildObj转换为超类对象ParentObj，ParentObj调用的是超类的成员变量A！
 * 注：1.隐藏成员变量时，只要同名即可，可以更改变量类型(无论基本类型还是隐藏类型)
 * 2.不能隐藏超类中的private成员变量，换句话说，只能隐藏可以访问的成员变量。
 * 3.隐藏超类成员变量A时，可以降低或提高子类成员变量B的访问权限，只要A不是private
 * 4.隐藏成员变量与是否静态无关！静态变量可以隐藏实例变量，实例变量也可以隐藏静态变量。
 * 5.可以隐藏超类中的final成员变量。
 *
 *
 *
 * 隐藏后的方法必须仍为静态方法！(静态方法------隐藏------>静态方法)
 * 隐藏静态方法：超类Parent有静态方法A，子类Child定义了与A“相同签名和子集返回类型”的静态方法B，子类对象ChildObj调用的是自己的静态方法B。
 * 如果把子类对象ChildObj转换为超类对象ParentObj，ParentObj调用的是超类的静态方法A！
 * 隐藏超类静态方法的语法规则与重写实例方法的规则几乎完全相同，只需要修改第(6)条为：(6)不能隐藏实例方法。。
 *
 *
 * final方法无法重写，无论是否为static方法，直接报错
 * private final == private，子类可以有重名的方法，
 */
public class InheritTest {
    public static void main(String args[]) {
        Person p;
        Chinese cn ;
        p = new Chinese();
        cn = new Chinese();
        cn = new Chinese("codersai");
        cn = new Chinese("codersai", 18);
        System.out.println(p.publicStr);
        System.out.println(cn.publicStr);
        System.out.println(cn.privateStr);

        p.prt("aaa");
        cn.prt("aaa");
    }
}

class Person {
    private String privateStr = "PersonPrivateStr";

    public static final String publicStr = "PersonPublicStr";

    public static void prt(String s) {
        System.out.println("person " + s);
    }

    public void fun() {
        System.out.println("Person fun");
    }

    Person() {
        prt("父类·无参数构造方法： "+"A Person.");
    }//构造方法(1)

    Person(String name) {
        prt("父类·含一个参数的构造方法： "+"A person's name is " + name);
    }//构造方法(2)
}

class Chinese extends Person {
    public String privateStr = "ChinesePrivateStr";

    public String publicStr = "ChinesePublicStr";

    public static void prt(String s) {
        System.out.println("chinese " + s);
    }

    public void fun() {
        System.out.println("Person fun");
    }

    Chinese() {
        super(); // 调用父类构造方法（1）
        prt("子类·调用父类”无参数构造方法“： "+"A chinese coder.");
    }

    Chinese(String name) {
        super(name);// 调用父类具有相同形参的构造方法（2）
        prt("子类·调用父类”含一个参数的构造方法“： "+"his name is " + name);
    }

    Chinese(String name, int age) {
        this(name);// 调用具有相同形参的构造方法（3）
        prt("子类：调用子类具有相同形参的构造方法：his age is " + age);
    }

    abstract class Foreigner{
        abstract void speak();
    }

}
