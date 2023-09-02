package me.fengorz.jvm.clazz;

/**
 * @Description 测试类加载的被动引用
 * @Author zhanshifeng
 * @Date 2020/10/13 6:28 PM
 */
public class SuperClass {
    static {
        System.out.println("super class init!");
    }

    public static int value = 666;
}
