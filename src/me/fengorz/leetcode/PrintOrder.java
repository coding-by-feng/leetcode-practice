package me.fengorz.leetcode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description TODO
 * @Author zhanshifeng
 * @Date 2020/6/28 10:12 PM
 */
public class PrintOrder {

    private ReentrantLock lock = new ReentrantLock();
    private Condition second = lock.newCondition();
    private Condition third = lock.newCondition();
    private AtomicInteger index = new AtomicInteger();


    public static void main(String[] args) {
        PrintOrder printOrder = new PrintOrder();
        printOrder.testFirst();
        printOrder.testSecond();
        printOrder.testThird();
        System.out.println("finish");
    }

    public PrintOrder() {
    }

    public void testFirst() {
        System.out.println("1");
        this.second.signalAll();
        index.incrementAndGet();
    }

    public void testSecond() {
        if (index.get() == 1) {
            System.out.println("2");
            index.incrementAndGet();
            return;
        }

        try {
            this.second.await();
            System.out.println("2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.third.signalAll();
            index.incrementAndGet();
        }
    }

    public void testThird() {
        if (index.get() == 2) {
            System.out.println("3");
            return;
        }

        try {
            this.third.await();
            System.out.println("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
