package com.flowerfat.apilibrary.phone;

import com.flowerfat.apilibrary.main.Http;

import org.json.JSONObject;

/**
 * Created by 明明大美女 on 2015/10/14.
 */
public class ApiPhone {

    private final String httpUrl = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone";

    public String phone2Place(String tel) {
        String httpArg = "tel=" + tel;
        String jsonResult = Http.request(httpUrl, httpArg);
        System.out.println(jsonResult);
        return dataBeautiful(jsonResult);
    }

    public String dataBeautiful(String text) {
        try {
            JSONObject jsonObject = new JSONObject(text);
            if (jsonObject.getInt("errNum") == 0) {
                JSONObject content = jsonObject.getJSONObject("retData");
                return content.getString("province") + "-" + content.getString("city");
            } else {
                return jsonObject.getString("retMsg");
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }


}
