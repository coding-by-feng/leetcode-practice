package me.fengorz.leetcode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Foo {

    public static void main(String[] args) {
        new Foo().second(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        });
    }

    private ReentrantLock lock = new ReentrantLock();
    private Condition second = lock.newCondition();
    private Condition third = lock.newCondition();
    private AtomicInteger index = new AtomicInteger(1);

    public Foo() {
    }

    public void first(Runnable printFirst) {

        // printFirst.run() outputs "first". Do not change or remove this line.
        this.lock.lock();
        printFirst.run();
        this.second.signalAll();
        index.set(2);
        lock.unlock();
    }

    public void second(Runnable printSecond) {

        // printSecond.run() outputs "second". Do not change or remove this line.
        if (index.get() == 2) {
            printSecond.run();
            index.set(3);
            return;
        }

        try {
            this.second.await();
            printSecond.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.third.signalAll();
            index.set(3);
        }
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        if (index.get() == 3) {
            printThird.run();
            return;
        }

        try {
            this.third.await();
            printThird.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}