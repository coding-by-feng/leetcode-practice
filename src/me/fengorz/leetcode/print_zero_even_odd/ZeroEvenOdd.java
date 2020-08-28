package me.fengorz.leetcode.print_zero_even_odd;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition zeroCondition = lock.newCondition();
    private final Condition evenCondition = lock.newCondition();
    private final Condition oddCondition = lock.newCondition();
    private final AtomicBoolean zeroPrint = new AtomicBoolean();
    private final AtomicBoolean oddPrint = new AtomicBoolean();

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            this.lock.lock();
            if (zeroPrint.get()) {
                this.zeroCondition.await();
            }
            printNumber.accept(0);
            zeroPrint.compareAndSet(false, true);
            if (oddPrint.get()) {
                this.evenCondition.signalAll();
            } else {
                this.oddCondition.signalAll();
            }
            this.lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            this.lock.lock();
            if (!zeroPrint.get() || !oddPrint.get()) {
                this.evenCondition.await();
            }
            printNumber.accept(i);
            zeroPrint.compareAndSet(true, false);
            oddPrint.compareAndSet(true, false);
            this.zeroCondition.signalAll();
            this.lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            this.lock.lock();
            if (!zeroPrint.get() || oddPrint.get()) {
                this.oddCondition.await();
            }
            printNumber.accept(i);
            zeroPrint.compareAndSet(true, false);
            oddPrint.compareAndSet(false, true);
            this.zeroCondition.signalAll();
            this.lock.unlock();
        }
    }
}
