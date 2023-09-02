package me.fengorz.jvm.scavenge;

import java.util.concurrent.TimeUnit;

/**
 * 测试通过finalize方法来逃脱GC
 *
 * @Author zhanshifeng
 * @Date 2020/9/8 5:48 PM
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC HOOK = null;

    public void isAlive() {
        System.out.println("yes.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.HOOK = this;// 逃脱GC
    }

    public static void main(String[] args) throws Throwable {
        HOOK = new FinalizeEscapeGC();

        testGC();
        testGC();
    }

    private static void testGC() throws InterruptedException {
        HOOK = null;
        System.gc();
        // finalize被调用的优先级很低，所以需要停留
        TimeUnit.SECONDS.sleep(1);
        if (HOOK != null) {
            HOOK.isAlive();
        } else {
            System.out.println("no.");
        }
    }
}
