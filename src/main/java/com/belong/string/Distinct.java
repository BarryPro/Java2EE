package com.belong.string;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * src.txt:是内容少的
 * out.txt:是内容多的
 * Created by belong on 2017/3/11.
 */
public class Distinct {
    public static void main(String[] args) {
        ditinct();
    }

    public static void ditinct(){
        URL pathSrc = SQL.class.getClassLoader().getResource("src.txt");
        URL pathOut = SQL.class.getClassLoader().getResource("out.txt");
        List<String> src = null;
        List<String> out = null;
        try {
            BufferedReader readerSrc =
                    new BufferedReader(new InputStreamReader(new FileInputStream(pathSrc.getPath())));
            BufferedReader readerOut =
                    new BufferedReader(new InputStreamReader(new FileInputStream(pathOut.getPath())));
            String text = "";
            src = new ArrayList();
            out = new ArrayList();
            while ((text = (readerSrc.readLine())) != null){
                src.add(text);
            }
            while ((text = (readerOut.readLine())) != null){
                out.add(text);
            }
            int count = 0;
            for (int i = 0; i < src.size(); i++) {
                for(int j = 0; j < out.size(); j++){
                    if(src.get(i).equals(out.get(j))){
                        System.out.println((count++)+src.remove(i));
                    }
                }
            }
            for(String str:out){
                System.out.println(str);
            }
        } catch (Exception e) {
            System.out.println("yichang");
            for(String str:out){
                System.out.println(str);
            }
        }

    }
}
