package me.fengorz.jvm;

import java.util.ArrayList;

/**
 * 模拟Java堆溢出
 * VM Args: -Xmx20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @Author zhanshifeng
 * @Date 2020/9/2 10:48 AM
 */
public class HeapOOM {

    private static class OOMObject {

    }

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }

}
