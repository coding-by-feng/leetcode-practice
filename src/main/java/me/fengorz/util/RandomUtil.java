package me.fengorz.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @Description TODO
 * @Author zhanshifeng
 * @Date 2023/8/31 22:12
 */
public class RandomUtil {

    public static int[] generateRandomUniqueArray(int size, int minValue, int maxValue) {
        if (maxValue - minValue + 1 < size) {
            throw new IllegalArgumentException("范围内不足够的数字可以生成不重复的数组");
        }

        int[] arr = new int[size];
        Set<Integer> set = new HashSet<>();

        Random rand = new Random();

        while (set.size() < size) {
            int num = rand.nextInt(maxValue - minValue + 1) + minValue;
            set.add(num);
        }

        Iterator<Integer> iterator = set.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            arr[i++] = iterator.next();
        }

        return arr;
    }

}
