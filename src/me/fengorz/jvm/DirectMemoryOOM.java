package me.fengorz.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 使用unsafe直接分配本机内存，模拟直接内存溢出
 * 越过了DirectByteBuffer类直接通过反射获取Unsafe实例进行内存分配
 * (Unsafe类的getUnsafe()方法指定只有引导类加载器才会返回实例，体现了设计者
 * 希望只有虚拟机标准类库里面的类才能使用Unsafe的功能，在JDK 10时才将Unsafe
 * 的部分功能通过VarHandle开放给外部使用)，因为虽然使用DirectByteBuffer分配
 * 内存也会拋出内存溢出异常，但它抛出异常时并没有真正向操作系统申请分配内
 * 存，而是通过计算得知内存无法分配就会在代码里手动抛出溢出异常，真正申请
 * 分配内存的方法是Unsafe:allocateMemory()。
 * <p>
 * VM Args: -Xmx20M -XX: MaxDirectMemorySize=10M
 *
 * @Author zhanshifeng
 * @Date 2020/9/4 3:31 PM
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }

}
