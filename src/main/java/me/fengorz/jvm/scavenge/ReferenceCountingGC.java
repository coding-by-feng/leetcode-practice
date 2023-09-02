package me.fengorz.jvm.scavenge;

/**
 * 单纯的引用计数就很难解决对象之间相互循环引用的问题。
 *
 * @Author zhanshifeng
 * @Date 2020/9/8 4:53 PM
 */
public class ReferenceCountingGC {

    public Object instance;
    private static final int _1MB = 1024 * 1024;
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC a = new ReferenceCountingGC();
        ReferenceCountingGC b = new ReferenceCountingGC();
        a.instance = b;
        b.instance = a;
        a = null;
        b = null;

        // 如果JVM单纯引用计数的话，a和b是不能被回收的，但是实际上是回收了。
        System.gc();
    }

    public static void main(String[] args) {
        testGC();
    }

}
