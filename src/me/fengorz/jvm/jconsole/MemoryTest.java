package me.fengorz.jvm.jconsole;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhanshifeng
 * @Date 2020/9/26 4:27 PM
 */
public class MemoryTest {

    /**
     * -Xms100m
     * -Xmx100m
     * -XX:+UseSerialGC
     * @param num
     * @throws InterruptedException
     */
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new LinkedList<>();
        for (int i = 0; i < num; i++) {
            TimeUnit.MILLISECONDS.sleep(50);
            list.add(new OOMObject());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        System.gc();
    }

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

}
