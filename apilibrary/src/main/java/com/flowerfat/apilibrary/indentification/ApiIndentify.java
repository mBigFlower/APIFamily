package com.flowerfat.apilibrary.indentification;

import com.flowerfat.apilibrary.main.Http;

import org.json.JSONObject;

/**
 * Created by Bigflower on 2015/10/18.
 *
 * 从身份证号中，自动获取以下信息
 * 地址
 * 生日
 * 性别
 */
public class ApiIndentify {

    private final String httpUrl = "http://apis.baidu.com/apistore/idservice/id";

    public String id2Details(String id) {
        String httpArg = "id=" + id;
        String jsonResult = Http.request(httpUrl, httpArg);
        System.out.println(jsonResult);
        return dataBeautiful(jsonResult);
    }

    public String dataBeautiful(String text) {
        try {
            JSONObject jsonObject = new JSONObject(text);
            if (jsonObject.getInt("errNum") == 0) {
                JSONObject content = jsonObject.getJSONObject("retData");
                StringBuffer sb = new StringBuffer();
                sb
                 .append("性别：").append("M".equals(content.getString("sex"))?"男":"女")
                 .append("\n生日：").append(content.getString("birthday"))
                 .append("\n地址：").append(content.getString("address"));
                return sb.toString();
            } else {
                return jsonObject.getString("errMsg");
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
