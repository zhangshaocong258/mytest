package com.szl;

/**
 * Created by zsc on 2016/9/8.
 */

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLTest {
    private static URL url;
    private static HttpURLConnection con;
    private static int state = -1;

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.zhihu.com");
        String urlStr = "https://www.zhihu.com/people/zsc";
        String str = "/detaila/login.asp?id=js001-2016-07-08-165237";
        String host = url.getHost();
        String path = url.getPath();
        String info = url.getUserInfo();
        String protocol = url.getProtocol();
        System.out.println(host);
        System.out.println(isPartDomainName(urlStr));
        System.out.println(path);
        System.out.println(info);
        System.out.println(protocol);

    }

    private static boolean isIpv4(String ipAddress) {

        String ip = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

        String ip2 = "\\b.*([0-9]{1,3}\\.){3}[0-9]{1,3}.*\\b";
        String ip3 = "\\b.*((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)).*\\b";


        Pattern pattern = Pattern.compile(ip2);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();

    }

    private static boolean isPartDomainName (String urlStr) {
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String host = url.getHost();
        return host.endsWith("zhihu.com");
    }
}
