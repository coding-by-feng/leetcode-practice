package me.fengorz.leetcode.concurrency.print_fooBar_alternately;
//我们提供一个类：
//
// 
//class FooBar {
//  public void foo() {
//    for (int i = 0; i < n; i++) {
//      print("foo");
//    }
//  }
//
//  public void bar() {
//    for (int i = 0; i < n; i++) {
//      print("bar");
//    }
//  }
//}
// 
//
// 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。 
//
// 请设计修改程序，以确保 "foobar" 被输出 n 次。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = 1
//输出: "foobar"
//解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
// 
//
// 示例 2: 
//
// 
//输入: n = 2
//输出: "foobarfoobar"
//解释: "foobar" 将被输出两次。
// 
// 👍 68 👎 0


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//leetcode submit region begin(Prohibit modification and deletion)
public class FooBar {
    private int n;

    private ReentrantLock lock;
    private Condition fooCondition;
    private Condition barCondition;
    private AtomicBoolean isFooPrint = new AtomicBoolean(false);

    public FooBar(int n) {
        this.n = n;
        this.lock = new ReentrantLock();
        this.fooCondition = lock.newCondition();
        this.barCondition = lock.newCondition();
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            this.lock.lock();
            if (isFooPrint.get()) {
                this.fooCondition.await();
            }
            printFoo.run();
            isFooPrint.compareAndSet(false, true);
            this.barCondition.signalAll();
            this.lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            this.lock.lock();
            if (!isFooPrint.get()) {
                this.barCondition.await();
            }
            printBar.run();
            isFooPrint.compareAndSet(true, false);
            this.fooCondition.signalAll();
            this.lock.unlock();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
