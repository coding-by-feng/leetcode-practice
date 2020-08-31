package me.fengorz.leetcode.concurrency.print_in_order;

import java.util.concurrent.atomic.AtomicInteger;

class Foo {

    private AtomicInteger second = new AtomicInteger();
    private AtomicInteger third = new AtomicInteger();

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        second.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        while (second.get() != 1) {
        }
        printSecond.run();
        third.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        while (third.get() != 1) {
        }
        printThird.run();

    }
}