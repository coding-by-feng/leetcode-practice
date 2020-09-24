package me.fengorz.jvm.serial_and_serial_old;

/**
 * 测试对象优先分配在Eden空间
 * <p>
 * -verbose:gc
 * -Xms20M
 * -Xmx20M
 * -Xmn10M
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 * <p>
 * 限制Java大小为20MB，不可扩展，10MB分配给Minor，剩下10MB给Major
 * Minor所在Eden空间与Survivor From空间的默认比率是8:1（Minor GC时存活对象从Eden移步到Survivor From时）
 *
 * @Author zhanshifeng
 * @Date 2020/9/24 4:16 PM
 */
public class PrioritizedEdenAllocation {
    private final static int _1MB = 1024 * 1024;

    public static void test() {
        byte[] allocation1 = new byte[2 * _1MB];
        byte[] allocation2 = new byte[2 * _1MB];
        byte[] allocation3 = new byte[2 * _1MB];
        /**
         * -Xmn给到Minor只有10MB，这个时候，上面已经消耗掉6个MB了，
         * 剩下的4MB不足以分配给4MB大小的allocation4，
         * 故Eden空间不足，出现一次Minor GC。
         * GC期间虚拟机又发现已有的三个2MB大小的对象全部无法放入Survivor From空间(Survivor From空间只有1MB大小)
         * 于是触发分配担保机制(Handle Promotion)，allocation1、allocation2、allocation3被转移到Major
         * 最后allocation4被成功分配达到Eden，此时Survivor空闲
         */
        byte[] allocation4 = new byte[4 * _1MB]; // GC

        // Heap
        // PSYoungGen total 9216K, used 7988K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
        // eden space 8192K, 97% used [0x00000007bf600000,0x00000007bfdcd1a0,0x00000007bfe00000)
        // from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
        // to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
        // ParOldGen       total 10240K, used 4096K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
        // 实际上这里的GC之后的老年代的10MB只使用到4MB，应该是Minor GC只回收了前面连个对象就足以分配allocation4了，所以没有回收第三个对象
        // object space 10240K, 40% used [0x00000007bec00000,0x00000007bf000010,0x00000007bf600000)
        // Metaspace       used 3050K, capacity 4496K, committed 4864K, reserved 1056768K
        // class space    used 333K, capacity 388K, committed 512K, reserved 1048576K
    }

    /**
     * 其他参数同上
     * -XX:+UseSerialGC（下面参数在Parallel Scavenge不支持）
     * -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation = new byte[4 * _1MB]; // 直接分配到老年代了

        // Heap
        // def new generation   total 9216K, used 1844K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
        // eden space 8192K,  22% used [0x00000007bec00000, 0x00000007bedcd170, 0x00000007bf400000)
        // from space 1024K,   0% used [0x00000007bf400000, 0x00000007bf400000, 0x00000007bf500000)
        // to   space 1024K,   0% used [0x00000007bf500000, 0x00000007bf500000, 0x00000007bf600000)
        // tenured generation   total 10240K, used 4096K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
        // 下面这行可以看到老年代被占用。
        // the space 10240K,  40% used [0x00000007bf600000, 0x00000007bfa00010, 0x00000007bfa00200, 0x00000007c0000000)
        // Metaspace       used 2981K, capacity 4496K, committed 4864K, reserved 1056768K
        // class space    used 328K, capacity 388K, committed 512K, reserved 1048576K
    }

    /**
     * 其他参数同上
     * -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold() {
        byte[] allocation1 = new byte[_1MB / 4];
        byte[] allocation2 = new byte[4 * _1MB];
        byte[] allocation3 = new byte[4 * _1MB]; // Eden控件这个时候不足4MB，进行第一次MinorGC，将allocation1移到Survivor From空间，将allocation2移到Major
        System.out.println("------------");
        allocation3 = null;
        allocation3 = new byte[4 * _1MB]; // 手动第二次GC，allocation1年龄是2，进入Major
        System.out.println("------------");
    }

    /**
     * 其他参数同上
     * -XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold2() {
        byte[] allocation1 = new byte[_1MB / 4];
        byte[] allocation2 = new byte[_1MB / 4]; // allocation1、allocation2加起来大于survivor空间的一半
        byte[] allocation3 = new byte[4 * _1MB];
        byte[] allocation4 = new byte[4 * _1MB]; // Eden空间不足，第一次GC，allocation1、allocation2直接进入Major，而非Survivor
        System.out.println("------------");
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
        System.out.println("------------");// 手动第二次GC，allocation3也进入Major，所以tenured generation占用48%
        // tenured generation   total 10240K, used 4967K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
        // the space 10240K,  48% used [0x00000007bf600000, 0x00000007bfad9f80, 0x00000007bfada000, 0x00000007c0000000)
    }

    public static void main(String[] args) {
        // test();
        // testPretenureSizeThreshold();
        // testTenuringThreshold();
        testTenuringThreshold2();
    }
}
