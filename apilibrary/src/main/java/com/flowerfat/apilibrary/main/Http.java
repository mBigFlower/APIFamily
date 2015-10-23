package com.flowerfat.apilibrary.main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Http {


    public static String getPhoneX(String httpUrl, String httpArg){
        return request(httpUrl, httpArg, "apix-key", ApiContants.apikeyx_phone);
    }
    public static String getCookX(String httpUrl, String httpArg){
        return request(httpUrl, httpArg, "apix-key", ApiContants.apikeyx_cook);
    }

    public static String request(String httpUrl, String httpArg) {
        return Util.decodeUnicode(request(httpUrl, httpArg, "appkey", ApiContants.apikeys));
    }

    public static String request(String httpUrl, String httpArg, String appkey1, String appkey2) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        System.out.println("请求地址："+httpUrl);
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");

            connection.setRequestProperty(appkey1, appkey2);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }

            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
