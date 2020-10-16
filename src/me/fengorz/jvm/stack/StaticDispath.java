package me.fengorz.jvm.stack;

import java.util.Random;

/**
 * @Description 静态分派Demo
 * <p>
 * 虚拟机（或者准确地说是编译器）在重载时是通过参数的静态类型而不是实际类型作为判定依据的。
 * @Author zhanshifeng
 * @Date 2020/10/16 8:16 PM
 */
public class StaticDispath {

    static abstract class Human {

    }

    static class Man extends Human {

    }

    static class Woman extends Human {

    }

    public void sayHello(Human mankind) {
        System.out.println("hello, mankind!");
    }

    public void sayHello(Man man) {
        System.out.println("hello, man!");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello, woman!");
    }

    /**
     * Human可理解过man或者woman的静态类型，静态类型在编译器就可知
     * man和woman还具备了一个实际类型，或者叫运行时类型，这要在运行器才能得知
     *
     * @param args
     */
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispath staticDispath = new StaticDispath();
        staticDispath.sayHello(man);
        staticDispath.sayHello(woman);

        // 实际类型变化，要运行时才能得知
        Human mankind = (new Random()).nextBoolean() ? new Man() : new Woman();
        // 静态类型变化，编译器即可得知
        staticDispath.sayHello((Man) mankind);
        staticDispath.sayHello((Woman) mankind);
    }

    /**
     * hello, mankind!
     * hello, mankind!
     */

}
