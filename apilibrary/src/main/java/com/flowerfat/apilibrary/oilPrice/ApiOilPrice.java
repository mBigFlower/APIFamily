package com.flowerfat.apilibrary.oilPrice;

import com.flowerfat.apilibrary.main.Http;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/10/18.
 *
 *  完全免费。不限量只限每秒10次的并发。可查询全国31个省的油价。
 */
public class ApiOilPrice {

    private final String httpUrl = "http://apis.baidu.com/showapi_open_bus/oil_price/find";

    public String phone2Place(String province) {
        String httpArg = "prov=" + province;
        String jsonResult = Http.request(httpUrl, httpArg);
        System.out.println(jsonResult);
        return dataBeautiful(jsonResult);
    }


    public String dataBeautiful(String text) {
        try {
            JSONObject jsonObject = new JSONObject(text);
            if (jsonObject.getInt("showapi_res_code") == 0) {
                JSONObject content = jsonObject.getJSONObject("showapi_res_body");
                JSONArray list = new JSONArray(content.getString("list"));

                return "";
            } else {
                return jsonObject.getString("showapi_res_error");
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }

}
