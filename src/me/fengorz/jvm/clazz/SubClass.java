package me.fengorz.jvm.clazz;

/**
 * @Description 测试类加载的被动引用
 * @Author zhanshifeng
 * @Date 2020/10/13 6:30 PM
 */
public class SubClass extends SuperClass {
    public String test = "init";

    static {
        System.out.println("sub class init!");
    }

}
