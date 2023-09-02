package me.fengorz.jvm.stack.frame;

/**
 * @Author zhanshifeng
 * @Date 2020/10/16 1:46 PM
 */
public class GCTest {

    public static void main(String[] args) {
        new GCTest().test3();
    }

    /**
     * [GC (System.gc())  69468K->66136K(251392K), 0.0019022 secs]
     * [Full GC (System.gc())  66136K->65940K(251392K), 0.0106349 secs]
     */
    public void test1() {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        // 这里操作GC，是不会回收placeholder，因为此时placeholder还在作用域之内
        System.gc();
    }

    /**
     * [GC (System.gc())  69468K->66136K(251392K), 0.0016431 secs]
     * [Full GC (System.gc())  66136K->65940K(251392K), 0.0053501 secs]
     */
    public void test2() {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        // 这里操作GC，依然不会回收placeholder
        System.gc();
    }

    /**
     * [GC (System.gc())  69468K->66104K(251392K), 0.0010494 secs]
     * [Full GC (System.gc())  66104K->404K(251392K), 0.0052462 secs]
     */
    public void test3() {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        // 这里操作GC，这次placeholder真的被回收了
        // 上面两个例子placeholder所占用的变量槽没有被复用，而这里的被复用了，老的placeholder对象实例失去引用自然会被回收
        System.gc();
    }

}
