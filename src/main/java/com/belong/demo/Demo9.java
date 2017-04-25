package com.belong.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: <p></p>
 * @Author: belong.
 * @Date: 2017/4/24.
 */
public class Demo9 {
    public static void main(String[] args) {
        URL url = Demo9.class.getClassLoader().getResource("file/regex.txt");
        try {
            int count = 0;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(url.getPath()))));
            String context = "";
            while((context = br.readLine())!=null){
                String doc_regex_beg = "/\\*\\*(.*)";
                String doc_regex_end = "(.*)\\*\\*/";
                boolean flag = false;
                Pattern doc_pattern_beg = Pattern.compile(doc_regex_beg,Pattern.DOTALL);
                Matcher doc_matter_beg = doc_pattern_beg.matcher(context);
                Pattern doc_pattern_end = Pattern.compile(doc_regex_end,Pattern.DOTALL);
                Matcher doc_matter_end = doc_pattern_end.matcher(context);
                if(doc_matter_beg.find()){
                    flag = false;
                }
                if(doc_matter_end.find()){
                    flag = true;
                }
                if(!flag){
                    String regex = "CREATE(.*)TABLE(.*)\\.(.*)";
                    Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(context);
                    if(matcher.find()){
                        System.out.println(matcher.group(3));
                        count++;
                    }
                }

            }
            System.out.println(count);

            // 忽略大小写并且多行进行匹配

            //System.out.println(buffer);

            //if(doc_matter.find()){
            //    //System.out.println(doc_matter.group(0));
            //    String regex = "CREATE(.*)TABLE (.*)\\.(.*)";
            //    Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
            //    Matcher matcher = pattern.matcher(buffer);
            //    if(matcher.find()){
            //        //System.out.println(matcher.group(3));
            //    }
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
