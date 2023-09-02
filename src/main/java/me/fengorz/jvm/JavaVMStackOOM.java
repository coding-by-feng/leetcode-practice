package me.fengorz.jvm;

/**
 * 模拟不断创建不会被销毁的线程而导致虚拟机栈溢出
 * VM Args: -Xss4m
 *
 * @Author zhanshifeng
 * @Date 2020/9/4 11:06 AM
 */
public class JavaVMStackOOM {

    private void neverStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    neverStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        // JavaVMStackOOM test = new JavaVMStackOOM();
        // test.stackLeakByThread();
    }

}
