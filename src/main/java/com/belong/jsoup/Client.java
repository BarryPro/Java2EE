package com.belong.jsoup;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: <p></p>
 * @Author: belong.
 * @Date: 2017/5/12.
 */
public class Client {

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8080/ssh/manage/Disk_add.action";
        String filePath = "test.mp3";

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        MultipartEntity reqEntity = new MultipartEntity();
        httpPost.setEntity(reqEntity);

        /** file param name */
        FileBody bin = new FileBody(new File(filePath));
        reqEntity.addPart("Filedata", bin);

        /** String param name */
        StringBody userId = new StringBody("1");
        reqEntity.addPart("userId", userId);

        System.out.println("executing: " + httpPost.getRequestLine());

        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();

        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());

        if (responseEntity != null){
            System.out.println("Response content: "
                    + inputStream2String(responseEntity.getContent()));
        }

        httpClient.getConnectionManager().shutdown();
    }

    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

}
