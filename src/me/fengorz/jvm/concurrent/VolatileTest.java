package me.fengorz.jvm.concurrent;

/**
 * @Description 基于volatile的可见性存在的高并发线程不安全问题测试
 * @Author zhanshifeng
 * @Date 2020/10/21 11:12 AM
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    public static final int THREADS_COUNT = 100;

    public static void main(String[] args) {
        /**
         * IDEA默认main主方法会开启两个线程
         * java.lang.ThreadGroup[name=main,maxpri=10]
         *     Thread[main,5,main]
         *     Thread[Monitor Ctrl-Break,5,main]
         */
        Thread.currentThread().getThreadGroup().list();
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(race);
    }

    /**
     *   public static void increase();
     *     descriptor: ()V
     *     flags: (0x0009) ACC_PUBLIC, ACC_STATIC
     *     Code:
     *       stack=2, locals=0, args_size=0
     *          // 当getstatic指令把race的值取到操作栈顶时，volatile关键字保证了race的值在此时是正确的
     *          0: getstatic     #2                  // Field race:I
     *          //  但是在执行iconst_1、iadd这些指令的时候，其他线程可能已经把race的值改变了，而操作栈顶的值就变成了过期的数据
     *          3: iconst_1
     *          4: iadd
     *          // 所以putstatic指令执行后就可能把较小的race值同步回主内存之中。
     *          5: putstatic     #2                  // Field race:I
     *          8: return
     *       LineNumberTable:
     *         line 13: 0
     *         line 14: 8
     */

}
