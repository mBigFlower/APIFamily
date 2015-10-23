package com.flowerfat.apilibrary.phone;

import com.flowerfat.apilibrary.main.Http;

import org.json.JSONObject;

/**
 * Created by 明明大美女 on 2015/10/14.
 */
public class ApiPhone {

    private final String httpUrl = "http://a.apix.cn/apixlife/phone/phone";

    public String phone2Place(String tel) {
        String httpArg = "phone=" + tel;
        String jsonResult = Http.getPhoneX(httpUrl, httpArg);
        System.out.println(jsonResult);
        return dataBeautiful(jsonResult);
    }

    public String dataBeautiful(String text) {
        try {
            JSONObject jsonObject = new JSONObject(text);
            if (jsonObject.getInt("error_code") == 0) {
                JSONObject content = jsonObject.getJSONObject("data");
                return content.getString("province") + "-" + content.getString("city") + "\n" + content.getString("operator");
            } else {
                return jsonObject.getString("message");
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }


}
