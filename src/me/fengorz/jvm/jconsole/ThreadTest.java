package me.fengorz.jvm.jconsole;

import java.io.IOException;

/**
 * @Author zhanshifeng
 * @Date 2020/9/26 4:51 PM
 */
public class ThreadTest {

    public static void createBusyThread() {
        new Thread(() -> {
            while (true) {

            }
        }, "testBusyThread").start();
    }

    private final static Object lock = new Object();

    public static void createLockThread() {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread").start();
    }

    static class SyncAdd implements Runnable {
        int a, b;

        public SyncAdd(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }

    /**
     * 为了做实验的机器资源安全，可以加上限制：
     * -Xms500m
     * -Xmx500m
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // reader.readLine();
        // createBusyThread();
        // reader.readLine();
        // createLockThread();

        for (int i = 0; i < 500; i++) {
            new Thread(new SyncAdd(1, 2)).start();
            new Thread(new SyncAdd(2, 1)).start();
        }
    }

    /**
     * Name: testBusyThread
     * State: RUNNABLE
     * Total blocked: 0  Total waited: 0
     *
     * Stack trace:
     * app//me.fengorz.jvm.jconsole.ThreadTest.lambda$createBusyThread$0(ThreadTest.java:15)
     * app//me.fengorz.jvm.jconsole.ThreadTest$$Lambda$96/0x000000080019b840.run(Unknown Source)
     * java.base@11.0.8/java.lang.Thread.run(Thread.java:834)
     */

    /**
     * Name: testLockThread
     * State: WAITING on java.lang.Object@b847d8d
     * Total blocked: 0  Total waited: 1
     *
     * Stack trace:
     * java.base@11.0.8/java.lang.Object.wait(Native Method)
     * java.base@11.0.8/java.lang.Object.wait(Object.java:328)
     * app//me.fengorz.jvm.jconsole.ThreadTest.lambda$createLockThread$1(ThreadTest.java:27)
     * app//me.fengorz.jvm.jconsole.ThreadTest$$Lambda$97/0x000000080019cc40.run(Unknown Source)
     * java.base@11.0.8/java.lang.Thread.run(Thread.java:834)
     */

    /**
     * 太多SyncAdd线程产生死锁之后可以点击JConsole的Detect Deadlock探测死锁。
     *
     * Name: Thread-45
     * State: BLOCKED on java.lang.Integer@2f98ef2a owned by: Thread-48
     * Total blocked: 2  Total waited: 0
     *
     * Stack trace:
     * app//me.fengorz.jvm.jconsole.ThreadTest$SyncAdd.run(ThreadTest.java:45)
     *    - locked java.lang.Integer@d0dcf29
     * java.base@11.0.8/java.lang.Thread.run(Thread.java:834)
     *
     * ----------------
     *
     * Name: Thread-48
     * State: BLOCKED on java.lang.Integer@d0dcf29 owned by: Thread-45
     * Total blocked: 2  Total waited: 0
     *
     * Stack trace:
     * app//me.fengorz.jvm.jconsole.ThreadTest$SyncAdd.run(ThreadTest.java:45)
     *    - locked java.lang.Integer@2f98ef2a
     * java.base@11.0.8/java.lang.Thread.run(Thread.java:834)
     *
     * 可以发现两个线程在相互等待锁的释放：
     * State: BLOCKED on java.lang.Integer@2f98ef2a owned by: Thread-48
     * State: BLOCKED on java.lang.Integer@d0dcf29 owned by: Thread-45
     */

}
