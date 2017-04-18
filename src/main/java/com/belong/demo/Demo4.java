package com.belong.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: belong.
 * @Description:
 * @Date: 2017/4/15.
 */
public class Demo4 {
    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
