package me.fengorz.jvm.clazz;

/**
 * VM Option: -XX:+TraceClassLoading
 *
 * @Author zhanshifeng
 * @Date 2020/10/13 6:31 PM
 */
public class ClassLoadTest {

    public static void main(String[] args) {
        // System.out.println(SubClass.value);
        // SuperClass[] arr = new SuperClass[10];
        // System.out.println(Constant.HELLO_WORLD);

        // SubClass obj1 = new SubClass();
        // final SubClass obj2 = obj1;
        // SubClass obj3 = obj1;
        // obj1.test = "1";
        //
        // SubClass obj = new SubClass();
        // String test = obj.test;
        // final String test1 = test;
        // String test2 = test;
        // test = "1";
        // System.out.println(obj1.test);
        // System.out.println(obj2.test);
        // System.out.println(obj3.test);
        // System.out.println(test);
        // System.out.println(test1);
        // System.out.println(test2);

        /**
         * 输出如下，可以看出基础类型对象实例的传递是新new一个实例，而引用对象类型的的实例传递才是真正的传递。
         * 1
         * 1
         * 1
         * 1
         * init
         * init
         */
    }

    /**
     * 并没有输出 "constant init!"
     */

}
