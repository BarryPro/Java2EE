package com.belong.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * @Description: <p></p>
 * @Author: belong.
 * @Date: 2017/4/26.
 */
public class HtmlString {
    public static void main(String[] args) {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        String url = null;

        //url = HtmlString.class.getClassLoader().getResource("file/soup.txt").getPath();

        //System.out.println(url);
        Document doc = Jsoup.parse(html, "D:\\jsoup.txt");
        System.out.println(doc);
    }
}
