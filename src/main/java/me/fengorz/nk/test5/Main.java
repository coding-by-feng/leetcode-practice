package me.fengorz.nk.test5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            String[] arr = line.split(" ");
            if (line.startsWith("insert")) {
                boolean isInsert = false;
                for (int i = 0; i < list.size(); i++) {
                    if (arr[1].equals(list.get(i))) {
                        list.add(i, arr[2]);
                        isInsert = true;
                        break;
                    }
                }
                if (!isInsert) {
                    list.add(arr[2]);
                }
            } else if (line.startsWith("delete")) {
                Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().equals(arr[1])) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        if (list.isEmpty()) {
            System.out.println("NULL");
            return;
        }
        System.out.println(String.join(" ", list));
    }

}