package com.szl;

/**
 * Created by zsc on 2018/4/9.
 * java初始化顺序
 *
 * (1)访问SubClass.main(),(这是一个static方法)，于是装载器就会为你寻找已经编译的SubClass类的代码（也就是SubClass.class文件）。
 *    在装载的过程中，装载器注意到它有一个基类（也就是extends所要表示的意思），于是它再装载基类。不管你创不创建基类对象，这个过程总会发生。
 *    如果基类还有基类，那么第二个基类也会被装载，依此类推。
 *
 * (2)执行根基类的static初始化，然后是下一个派生类的static初始化，依此类推。这个顺序非常重要，
 *    因为派生类的“static初始化”有可能要依赖基类成员的正确初始化。
 *
 * (3)当所有必要的类都已经装载结束，开始执行main()方法体，并用new SubClass（）创建对象。
 *
 * (4)类SubClass存在父类，则调用父类的构造函数，你可以使用super来指定调用哪个构造函数。基类的构造过程以及构造顺序，同派生类的相同。
 *    首先基类中各个变量按照字面顺序进行初始化，然后执行基类的构造函数的其余部分。
 *
 * (5)对子类成员数据按照它们声明的顺序初始化，执行子类构造函数的其余部分。
 *
 * https://segmentfault.com/a/1190000004527951
 */
class Parent {
    /* 静态变量 */
    public static String p_StaticField = "父类--静态变量";
    /* 变量 */
    public String    p_Field = "父类--变量";
    protected int    i    = 9;
    protected int    j    = 0;
    /* 静态初始化块 */
    static {
        System.out.println( p_StaticField );
        System.out.println( "父类--静态初始化块" );
    }
    /* 初始化块 */
    {
        System.out.println( p_Field );
        System.out.println( "父类--初始化块" );
    }
    /* 构造器 */
    public Parent()
    {
        System.out.println( "父类--构造器" );
        System.out.println( "i=" + i + ", j=" + j );
        j = 20;
    }
}

public class LoadOrderTest extends Parent {
    /* 静态变量 */
    public static String s_StaticField = "子类--静态变量";
    /* 变量 */
    public String s_Field = "子类--变量";
    /* 静态初始化块 */
    static {
        System.out.println( s_StaticField );
        System.out.println( "子类--静态初始化块" );
    }
    /* 初始化块 */
    {
        System.out.println( s_Field );
        System.out.println( "子类--初始化块" );
    }
    /* 构造器 */
    public LoadOrderTest()
    {
        System.out.println( "子类--构造器" );
        System.out.println( "i=" + i + ",j=" + j );
    }


    /* 程序入口 */
    public static void main( String[] args )
    {
        System.out.println( "子类main方法" );
        new LoadOrderTest();
    }
}
/* Output:
父类--静态变量
父类--静态初始化块
子类--静态变量
子类--静态初始化块
子类main方法
父类--变量
父类--初始化块
父类--构造器
i=9, j=0
子类--变量
子类--初始化块
子类--构造器
i=9,j=20
*///:~