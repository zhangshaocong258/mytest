package com.szl;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * Created by zsc on 2016/11/29.
 * 知乎下载验证码
 */
public class ZhiHuCaptchaTest {
    public static void main(String[] args) throws IOException {
        downloadImage();
    }

    public static void downloadImage() throws IOException {
        String folder = "E:\\img";
        File file = new File(folder);
        if (!file.exists()) {
            file.mkdirs();
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.zhihu.com/captcha.gif?type=login");
        CloseableHttpResponse response = null;
        for (int i = 0; i < 100; i++) {
            response = httpClient.execute(httpGet);
            HttpEntity httpEntity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                String path = folder + File.separator + i + ".png";
                InputStream inputStream = httpEntity.getContent();
                OutputStream outStream = new FileOutputStream(path);
                IOUtils.copy(inputStream, outStream);
                outStream.close();
                System.out.println(i + " OK!");
            }
            // 释放连接
            response.close();//关闭

        }
    }
}

