package com.belong.regex;

import com.belong.jsoup.Convert;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: <p></p>
 * @Author: belong.
 * @Date: 2017/5/12.
 */
public class Pager {
    public static void main(String[] args) {
        Pager pager = new Pager();
        String html = pager.getDecodeHtml();
        System.out.println(pager.getVideoDetail(html));
        //System.out.println(html);
        //pager.getPager(html);
        //System.out.println(pager.getSpecificUrl("http://www.80s.tw/movie/list/d----","0"));
        //pager.addVideo(html);
        //System.out.println(pager.getHtml("http://t.dyxz.la/upload/img/201703/poster_20170317_3343682_b.jpg!list","utf-8"));
    }

    public Map<String, Object> getVideoDetail(String html) {
        Map<String, Object> map = new HashMap<>();
        Document document = Jsoup.parse(html);
        Elements videoDetail = document.getElementsByClass("info");
        Elements videoPic_1 = document.getElementsByClass("noborder block1");
        Elements videoHref_1 = document.getElementsByClass("xunlei dlbutton1");
        if (!videoDetail.isEmpty()) {
            Element div = videoDetail.get(0);
            System.out.println(div.html().getBytes());
            Blob blob = null;
            try {
                blob = new SerialBlob(div.html().getBytes());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            map.put("videoInfo",blob);
        }
        if (!videoPic_1.isEmpty()) {
            Element div = videoPic_1.get(0);
            Elements imgs = div.getElementsByTag("img");
            if (!imgs.isEmpty()) {
                String videoPic = imgs.get(0).attr("_src");
                videoPic = "http:"+videoPic;
                map.put("videoPic",videoPic);
            }
        }
        if (!videoHref_1.isEmpty()) {
            Element spans = videoHref_1.get(0);
            Elements as = spans.getElementsByTag("a");
            if (!as.isEmpty()) {
                String url = as.get(0).attr("href");
                Convert convert = new Convert();
                String encodeHtml = convert.getHtml("http://tool.xd547.com/", url);
                String videoHref = getEncodeUrl(encodeHtml);
                map.put("videoHref",videoHref);
            }
        }
        return map;
    }

    public String getEncodeUrl(String html) {
        Document document = Jsoup.parse(html);
        String EncodeUrl = "";
        Elements divs = document.getElementsByClass("url-output");
        if (!divs.isEmpty()) {
            Elements as = divs.get(0).getElementsByTag("a");
            if (!as.isEmpty()) {
                EncodeUrl = as.get(0).attr("href");
            }
        }
        return EncodeUrl;
    }

    public String getSpecificUrl(String root, String pager) {
        String url = null;
        String regex = "(.*)/.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(root);
        if (matcher.find()) {
            if ("0".equals(pager)) {
                pager = "";
            }
            url = matcher.group(1) + "/" + pager;
        }
        return url;
    }

    public void addVideo(String html) {
        Document document = Jsoup.parse(html);
        Elements uls = document.getElementsByClass("me1 clearfix");
        Elements lis = uls.get(0).getElementsByTag("li");
        for (Element li : lis) {
            Elements as = li.getElementsByTag("a");
            Elements imgs = li.getElementsByTag("img");
            String videoImg = imgs.get(0).attr("_src");
            // 组合规范的图片访问URL
            videoImg = "http:" + videoImg;
            String videoHref = as.get(0).attr("href");
            String[] title = as.get(0).attr("title").toString().split("\\s+");
            // 分割title 分成title 和 rating
            String videoTitle = null;
            String videoRating = null;
            if (title != null) {
                videoTitle = title[0];
                videoRating = title[1];
            }
            System.out.println("videoHref:" + videoHref + "  videoTitle:" + videoTitle + " videoRating:" + videoRating + " videoImg:" + videoImg);
        }
    }


    public String getPager(String html) {
        String pager = null;
        String regex = "</strong>.*<a href=\".*/(.*)\">.*</a>\\s+</div>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        System.out.println("start");
        if (matcher.find()) {
            System.out.println("页数是：" + matcher.group(1));
        }
        System.out.println("end");
        return pager;
    }

    public String getDecodeHtml() {
        String root = "http://www.80s.tw/movie/20578";
        return getHtml(root, "utf-8");
    }

    public String getHtml(String url, String charset) {
        String html = null;
        try {
            // 获取客户端,使用客户端来进行网络请求
            HttpClient httpClient = HttpClients.createDefault();
            // 声明请求方法(相当于request的get请求方式)
            HttpGet httpGet = new HttpGet(url);
            // 返回请求的响应
            HttpResponse response = httpClient.execute(httpGet);
            // 得到响应状态
            StatusLine statusLine = response.getStatusLine();
            // 得到请求响应码
            int code = statusLine.getStatusCode();
            System.out.println("请求返回的状态码是：" + code);
            //判断响应状态码
            if (code == HttpStatus.SC_OK) {
                // 得到网页的实体
                HttpEntity entity = response.getEntity();
                // 转换成字符串
                html = EntityUtils.toString(entity, charset);
            }
        } catch (Exception e) {
            // 可以进行一直访问网页，防止中断
            System.out.println("异常信息是：" + e);
            return getHtml(url, charset);
        }
        return html;
    }

}
