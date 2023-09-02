package me.fengorz.nk.test4;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Deque<String> queue = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String line = in.nextLine();
            String[] arr = line.split(" ");
            if (line.startsWith("push")) {
                queue.push(arr[1]);
            } else if (line.startsWith("pop")) {
                if (queue.isEmpty()) {
                    System.out.println("error");
                } else {
                    System.out.println(queue.pollLast());
                }
            } else if (line.startsWith("front")) {
                if (queue.isEmpty()) {
                    System.out.println("error");
                } else {
                    System.out.println(queue.peekLast());
                }
            }
        }
    }
}