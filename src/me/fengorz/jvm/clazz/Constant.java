package me.fengorz.jvm.clazz;

/**
 * @Description 测试类加载的被动引用
 * @Author zhanshifeng
 * @Date 2020/10/13 6:43 PM
 */
public class Constant {
    static {
        System.out.println("constant init!");
    }

    public static final String HELLO_WORLD = "hello world!";
}
