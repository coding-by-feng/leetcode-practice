package me.fengorz.leetcode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * @Description TODO
 * @Author zhanshifeng
 * @Date 2020/8/3 9:16 PM
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        // FooBarTest();
        // zeroEvenOddTest();
        // h2o();
        fizzBuzzTest();
    }

    private static void FooBarTest() {
        FooBar fooBar = new FooBar(100);
        Runnable foo = () -> {
            System.out.print("foo");
        };
        Runnable bar = () -> {
            System.out.print("bar");
        };
        Thread thread1 = new Thread(() -> {
            try {
                fooBar.foo(foo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                fooBar.bar(bar);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void zeroEvenOddTest() {
        ZeroEvenOdd test = new ZeroEvenOdd(100);
        Runnable even = () -> {
            try {
                test.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable odd = () -> {
            try {
                test.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable zero = () -> {
            try {
                test.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(even);
        Thread thread2 = new Thread(odd);
        Thread thread3 = new Thread(zero);

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void h2o() {
        // BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
        // String[] arr = new String[]{"H", "O", "H", "O", "H", "H", "H", "O", "H", "O", "H", "H", "H", "H", "O"};
        // Arrays.stream(arr).peek(queue::add);
        H2O test = new H2O();
        Runnable h = () -> {
            AtomicInteger i = new AtomicInteger(100);
            while (i.get() > 0) {
                try {
                    test.hydrogen(() -> {
                        System.out.print("H");
                        i.getAndDecrement();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable o = () -> {
            AtomicInteger i = new AtomicInteger(50);
            while (i.get() > 0) {
                try {
                    test.oxygen(() -> {
                        System.out.print("O");
                        i.getAndDecrement();
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(h);
        Thread t2 = new Thread(o);

        t2.start();
        t1.start();
    }

    public static void fizzBuzzTest() throws InterruptedException {
        int n = 15;
        FizzBuzz fizzBuzz = new FizzBuzz(n);
        Runnable fizzPrint = () -> {
            System.out.print("fizz ");
        };
        Runnable buzzPrint = () -> {
            System.out.print("buzz ");
        };
        Runnable fizzBuzzPrint = () -> {
            System.out.print("fizzbuzz ");
        };
        IntConsumer printNumber = value -> {
            System.out.print(value + " ");
        };

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    fizzBuzz.fizz(fizzPrint);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    fizzBuzz.buzz(buzzPrint);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                for (int i = 0; i < n; i++) {
                    fizzBuzz.fizzbuzz(fizzBuzzPrint);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(() -> {
            try {
                fizzBuzz.number(printNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
