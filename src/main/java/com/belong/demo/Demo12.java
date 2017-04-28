package com.belong.demo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @Description: <p></p>
 * @Author: belong.
 * @Date: 2017/4/27.
 */
public class Demo12 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNext()) {
            int num = cin.nextInt();
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < num; i++) {
                set.add(cin.nextInt());
            }
            if(set.size()>=3){
                Object[] objects = set.toArray();
                Arrays.sort(objects);
                System.out.println(objects[2]);
            } else {
                System.out.println(-1);
            }
        }
    }
}
