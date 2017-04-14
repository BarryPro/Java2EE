package com.belong.demo;

/**
 * Created by belong on 2017/3/17.
 */
public class Demo1 {
    /**
     *
     * @param array :传进来的数据
     * @return ：返回所有叶子节点的路径和
     */
    public int binaryTreeSum(int [] array){
        // 定义和变量
        int sum = 0;
        // 1.把传入的数组解析成一位十进制数表示的形式
        // 用于存放一位十进制数的数组
        // 个位
        int []array_V = new int[array.length];
        // 十位
        int []array_P = new int[array.length];
        // 百位
        int []array_L = new int[array.length];
        for(int i = 0; i < array.length; i++){
            // 得到个位
            // 2.分离
            array_V[i] = array[i] % 10;
            array_P[i] = array[i] / 10 % 10;
            array_L[i] = array[i] / 10 / 10 % 10;
        }

        // 3.进行求和
        for(int i = 0 ; i < array.length - 1 ; i++){
            if(array_L[i] <= array_L[i+1]){
                if(array_P[i] <= array_P[i+1]){
                    sum = sum + array_V[i];                }
            }
        }
        sum = sum + array_V[0]*(array_V.length-1);




        return sum;
    }

    public static void main(String[] args) {
        int []array = {113,215,211};
        System.out.println(new Demo1().binaryTreeSum(array));
    }
}
