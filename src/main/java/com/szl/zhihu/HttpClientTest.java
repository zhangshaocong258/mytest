package com.szl.zhihu;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;

/**
 * Created by zsc on 2016/11/4.
 * 抛出异常，再catch得到2个超时错误
 */
public class HttpClientTest {
    public static void main(String[] args){
        try {
            HttpClientTest.download();
        } catch (Exception e) {
            System.out.println("超时");
            e.printStackTrace();
        }
    }

    public static void download() throws Exception{
        String path = "http://www.google.com";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(path);
        //建造者模式
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(1000).
                setConnectTimeout(1000).build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            HttpEntity httpEntity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            //如果是转移
            if (statusLine.getStatusCode() == HttpStatus.SC_MOVED_PERMANENTLY ||
                    statusLine.getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY ||
                    statusLine.getStatusCode() == HttpStatus.SC_SEE_OTHER ||
                    statusLine.getStatusCode() == HttpStatus.SC_TEMPORARY_REDIRECT) {
                Header header = httpGet.getFirstHeader("location");
                if(header != null){
                    String newUrl = header.getValue();
                    if(newUrl == null || newUrl.equals("")) {
                        newUrl = "/";
                        HttpGet redirect = new HttpGet(newUrl);
                    }
                }
            }

            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                if (httpEntity == null) {
                    throw new ClientProtocolException("Response contains no content");
                } else {
                    InputStream inputStream = httpEntity.getContent();
                    try {
                        // do something useful
//                        String filename = getFilenameByUrl(path,httpEntity.getContentType().getValue());
                        System.out.println(httpEntity.getContentType().getValue());
                        System.out.println(httpEntity.getContentType());
                    } finally {
                        inputStream.close();
                    }
                }
            }
        } finally {
            response.close();
        }
    }
}
