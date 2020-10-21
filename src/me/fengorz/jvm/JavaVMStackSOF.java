package me.fengorz.jvm;

/**
 * 减少栈内存容量来模拟栈溢出，或者可以同时在一个方法中大量声明本地变量（局部变量）
 * VM Args: -Xss160k
 *
 * @Author zhanshifeng
 * @Date 2020/9/4 10:31 AM
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF test = new JavaVMStackSOF();
        try {
            test.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + test.stackLength);
            throw e;
        }
    }

}
