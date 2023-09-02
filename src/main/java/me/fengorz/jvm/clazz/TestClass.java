package me.fengorz.jvm.clazz;

/**
 * 测试分析Class字节码
 *
 * @Author zhanshifeng
 * @Date 2020/10/7 12:39 PM
 */
public class TestClass {

    private int m;

    private int inc() {
        return m + 1;
    }

    /**
     * $ javap -v TestClass
     * Warning: File ./TestClass.class does not contain class TestClass
     * Classfile /Users/zhanshifeng/IdeaProjects/leetcode-practice/out/production/leetcode-practice/me/fengorz/jvm/clazz/TestClass.class
     * Last modified Oct 7, 2020; size 395 bytes
     * MD5 checksum acbed0d4b1041fe8c4a659f1f3dbb206
     * Compiled from "TestClass.java"
     * public class me.fengorz.jvm.clazz.TestClass
     * minor version: 0
     * major version: 52
     * flags: (0x0021) ACC_PUBLIC, ACC_SUPER   // 这里可以发现两个标志位为真：类声明为public，JDK 1.2之后的ACC_SUPER都必须为真，所以flags的值等于0x0001|0x0020=0x0021
     * this_class: #3                          // me/fengorz/jvm/clazz/TestClass     这里的类索引通过CONSTANT_Class_info类型的常量中的索引值可以找到定义在CONSTANT_Utf8_info类型的常量中的全限定名字符串："me/fengorz/jvm/clazz/TestClass"
     * super_class: #4                         // java/lang/Object   父类索引，原理和类索引一样。
     * interfaces: 0, fields: 1, methods: 2, attributes: 1   // 接口索引集合大小为0 // 字段计数器fields为1，代表类中有1个字段 // 方法计数器methods为2，代表类中有2个方法 // 属性表数量1个
     * Constant pool:                            // 常量池开始
     * #1 = Methodref          #4.#18         // java/lang/Object."<init>":()V CONSTANT_Methodref_info类型常量，即inc方法的符号引用
     * #2 = Fieldref           #3.#19         // me/fengorz/jvm/clazz/TestClass.m:I
     * #3 = Class              #20            // me/fengorz/jvm/clazz/TestClass     "#3"被上面this_class引用
     * #4 = Class              #21            // java/lang/Object   "#4"被上面super_class引用
     * #5 = Utf8               m              // CONSTANT_Utf8_info型常量用来描述字段m的名字
     * #6 = Utf8               I              // 声明字段数据类型为int
     * #7 = Utf8               <init>         // 编译器添加的实例构造器
     * #8 = Utf8               ()V            // 代表空参数返回void的inc方法
     * #9 = Utf8               Code           // inc方法的"Code"属性，说明此属性是方法具体的字节码指令
     * #10 = Utf8               LineNumberTable
     * #11 = Utf8               LocalVariableTable
     * #12 = Utf8               this
     * #13 = Utf8               Lme/fengorz/jvm/clazz/TestClass;
     * #14 = Utf8               inc
     * #15 = Utf8               ()I
     * #16 = Utf8               SourceFile
     * #17 = Utf8               TestClass.java
     * #18 = NameAndType        #7:#8          // "<init>":()V
     * #19 = NameAndType        #5:#6          // m:I                // 对应字典m的名字和类型
     * #20 = Utf8               me/fengorz/jvm/clazz/TestClass
     * #21 = Utf8               java/lang/Object
     * {
     * public me.fengorz.jvm.clazz.TestClass();
     * descriptor: ()V
     * flags: (0x0001) ACC_PUBLIC      // 标志位，方法声明为public
     * Code:
     * // 操作数栈深度1，本地（局部）变量表容量1（这个实际上是隐藏的this关键字），
     * // 这里args_size之所以为1，是因为局部变量表中至少会存在一个指向当前对象实例的局部变量this，
     * // 局部变量表中也会预留出第一个变量槽位来存放对象实例的引用，所以实例方法参数大小值从1开始
     * stack=1, locals=1, args_size=1
     * 0: aload_0                     // aload_0指令是将第0个变量槽中为reference类型的本地变量推到操作数栈顶
     * <p>
     * // 具体的方法调用，invokespecial，这条指令的作用是以栈顶的reference类型的数据所指向的对象作为方法接收者，
     * // 调用此对象的实例构造器方法、private方法或者它的父类的方法。这个方法有一个u2类型的参数说明具体调用哪个方法，
     * // 它指向常量池中的一个CONSTANT_Methodref_info类型常量，即此方法的符号引用。
     * 1: invokespecial #1            // Method java/lang/Object."<init>":()V
     * 4: return                      // inc方法的返回
     * LineNumberTable:
     * line 9: 0
     * LocalVariableTable:
     * Start  Length  Slot  Name   Signature
     * 0       5     0  this   Lme/fengorz/jvm/clazz/TestClass;
     * }
     * SourceFile: "TestClass.java"
     */

}
