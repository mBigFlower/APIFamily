package com.flowerfat.apilibrary.healthFood;

import com.flowerfat.apilibrary.main.Http;

import org.json.JSONObject;

/**
 * Created by 明明大美女 on 2015/10/18.
 */
public class ApiFood {

    private final String categoryUrl = "http://apis.baidu.com/apistore/mobilephoneservice/mobilephone";
    private final String byNameUrl = "http://apis.baidu.com/tngou/cook/name" ;
    private final String byIdUrl = "http://apis.baidu.com/tngou/cook/show" ;
    private final String listUrl = "http://apis.baidu.com/tngou/cook/list" ;

    /**
     * 获得菜谱列表，三个参数均 非必须
     * @param id 分类ID 默认0
     * @param page 页数 默认1
     * @param rows 条数 默认20
     * @return
     */
    public String getList(int id, int page, int rows){
        String httpArg = "id=" + id+"&page="+page+"&rows="+rows;
        String jsonResult = Http.request(byNameUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
    }

    /**
     * 通过名字，获得该菜名的做法
     * @param name
     * @return
     */
    public String getByName(String name){
        String httpArg = "name=" + name;
        String jsonResult = Http.request(byNameUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
    }

    /**
     * 通过菜的ID，获得该菜的做法
     * @param id
     * @return
     */
    public String getById(String id){
        String httpArg = "id=" + id;
        String jsonResult = Http.request(byIdUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
    }

    /**
     * 获得菜谱分类
     * @param page 非必须，默认为0
     * @return
     */
    public String getCategory(int page){
        String httpArg = "id=" + page;
        String jsonResult = Http.request(categoryUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
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





    /**
     * 通过服务器返回，获得图片链接
     * @param imgUrl  服务器返回的图片地址（拼接用）
     * @return 最终图片地址
     */
    public String getImgUrl(String imgUrl){
        return "http://tnfs.tngou.net/img"+imgUrl;
    }

    /**
     * 获得图片链接
     * 可自定义图片大小，这个有点猛哦。
     *
     * 带测试
     *
     * @param imgUrl  服务器返回的图片地址（拼接用）
     * @param width   图片宽度，貌似不能超过 800
     * @param height  图片高度，貌似不能超过 600
     * @return 最终图片地址
     */
    public String getImgUrlWH(String imgUrl, int width, int height){
        if(width > 800){

        } else if(height > 600){

        }
        return "http://tnfs.tngou.net/image"+imgUrl+"_"+width+"x"+height;
    }
}
