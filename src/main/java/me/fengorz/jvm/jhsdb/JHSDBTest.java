package me.fengorz.jvm.jhsdb;

/**
 * @Description 测试JHSDB故障分析工具
 * @Author zhanshifeng
 * @Date 2020/9/26 10:11 AM
 */
public class JHSDBTest {

    /**
     * taticObj随着Test的类型信息存放在方法区；
     * instanceObj随着Test的对象实例存放在Java堆；
     * localObj则是存放在foo方法栈帧的局部变量表中。
     */
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done");// 打断点
        }
    }

    private static class ObjectHolder {
    }

    /**
     * VM Options:
     * -Xmx10m
     * -XX:+UseSerialGC
     * -XX:-UseCompressedOops
     *
     * @param args
     */
    public static void main(String[] args) {
        Test test = new JHSDBTest.Test();
        test.foo();
    }

    /**
     * ./jps -l
     * 6019 me.fengorz.jvm.jhsdb.JHSDBTest
     *
     * ./jhsdb hsdb -pid 6019
     *
     * 通过Tools->Heep Parameters复制Eden里面对象的内存地址
     * scanoops 0x0000000124c00000 0x0000000124eb0000
     * 可以追寻到以下结果：
     * 0x0000000124e94a38 me/fengorz/jvm/jhsdb/JHSDBTest$ObjectHolder
     * 0x0000000124e94a48 me/fengorz/jvm/jhsdb/JHSDBTest$Test
     * 0x0000000124e94a60 me/fengorz/jvm/jhsdb/JHSDBTest$ObjectHolder
     * 0x0000000124e94a70 me/fengorz/jvm/jhsdb/JHSDBTest$ObjectHolder
     *
     * 通过Tools->Inspector确认三个地址具体的对象
     * _metadata._klass: InstanceKlass for me/fengorz/jvm/jhsdb/JHSDBTest$ObjectHolder
     * _super: InstanceKlass for java/lang/Object
     * _layout_helper: 16
     * _access_flags: 538968096
     * _subklass: null
     * _next_sibling: InstanceKlass for me/fengorz/jvm/jhsdb/JHSDBTest$Test
     * _vtable_len: 5
     * _array_klasses: null
     * _nonstatic_field_size: 0
     * _static_field_size: 0
     * _static_oop_field_count: 0
     * _nonstatic_oop_map_size: 0
     * _is_marked_dependent: 0
     * _init_state: 4
     * _itable_len: 2
     *
     */

    /**
     * hsdb> revptrs 0x0000000115c93cf0
     * null
     * Oop for java/lang/Class @ 0x0000000115c92288
     */

}
