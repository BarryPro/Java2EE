package com.belong.demo;

import java.util.*;

/**
 * Created by belong on 2017/3/18.
 */
public class Demo1Test {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext())
        {
            ArrayList<Integer> price = new ArrayList<>();
            int goodsNum = 0, buyNum = 0;
            HashMap<String, Integer> buyList = new HashMap<>();
            goodsNum = sc.nextInt();
            buyNum = sc.nextInt();
            for (int i = 0; i < goodsNum; i++)
            {
                price.add(sc.nextInt());
            }
            for (int i = 0; i < buyNum; i++)
            {
                String good = sc.next();
                if (buyList.containsKey(good))
                {
                    int count = buyList.get(good);
                    buyList.put(good, ++count);
                }
                else
                    buyList.put(good, 1);
            }
            int maxValue = 0;
            int minValue = 0;
            Collection collection = buyList.values();
            ArrayList<Integer> list = new ArrayList<>(collection);
            Collections.sort(list);
            Collections.sort(price);
            for (int i = 0; i < list.size(); i++)
            {
                minValue += list.get(list.size() - i - 1) * price.get(i);
                maxValue += list.get(list.size() - i - 1) * price.get(price.size() - i - 1);
            }
            System.out.printf(minValue + " " + maxValue + "\n");
        }
    }
}
