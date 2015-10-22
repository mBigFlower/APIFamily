package com.flowerfat.apilibrary.healthCook;

import android.util.Log;

import com.flowerfat.apilibrary.main.Http;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 明明大美女 on 2015/10/18.
 * <p/>
 * URLEncoder.encode(name) 这个方法呗废弃了。咋办？
 */
public class ApiCook {

    private final String categoryUrl = "http://apis.baidu.com/tngou/cook/classify";
    private final String byNameUrl = "http://apis.baidu.com/tngou/cook/name";
    private final String byIdUrl = "http://apis.baidu.com/tngou/cook/show";
    private final String listUrl = "http://apis.baidu.com/tngou/cook/list";

    private final Gson mGson = new Gson();

    public ApiCook() {
    }

    /**
     * 获得菜谱列表，三个参数均 非必须
     *
     * @param id   分类ID 默认0
     * @param page 页数 默认1
     * @param rows 条数 默认20
     * @return
     */
    public String getList(int id, int page, int rows) {
        String httpArg = "id=" + id + "&page=" + page + "&rows=" + rows;
        String jsonResult = Http.request(listUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
    }

    /**
     * 总
     * 通过名字，获得该菜名的做法
     *
     * @param name
     * @return
     */
    public String getByName(String name) {
        String httpArg = "name=" + URLEncoder.encode(name);
        String jsonResult = Http.request(byNameUrl, httpArg);
        return jsonResult;
    }

    /**
     * 通过菜的ID，获得该菜的做法
     *
     * @param id
     * @return
     */
    public Cook getById(int id) {
        String httpArg = "id=" + id;
        String jsonResult = Http.request(byIdUrl, httpArg);
        Log.i("getById", jsonResult);
        return mGson.fromJson(jsonResult, Cook.class);
    }

    /**
     * 获得菜谱分类
     *
     * @param page 非必须，默认为0
     * @return
     */
    public String getCategory(int page) {
        String httpArg = "id=" + page;
        String jsonResult = Http.request(categoryUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
    }
//////////////////////////////////////////////////////////////////
    // 上面四个是基本接口
    // 下面的是扩展
//////////////////////////////////////////////////////////////////

    /**
     * 总
     * 通过名字，获得该菜名的做法的列表
     *
     * @param name
     * @return
     */
    public List<Cook> getListByName(String name) {
        String httpArg = "name=" + URLEncoder.encode(name);
        String jsonResult = Http.request(byNameUrl, httpArg);
        try {
            return mGson.fromJson(jsonResult, new TypeToken<List<Cook>>(){}.getType());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过服务器返回，获得图片链接
     *
     * @param imgUrl 服务器返回的图片地址（拼接用）
     * @return 最终图片地址
     */
    public static String getImgUrl(String imgUrl) {
        return "http://tnfs.tngou.net/image" + imgUrl;
    }

    /**
     * 获得图片链接
     * 可自定义图片大小，这个有点猛哦。
     * <p/>
     * 带测试
     *
     * @param imgUrl 服务器返回的图片地址（拼接用）
     * @param width  图片宽度，貌似不能超过 800
     * @param height 图片高度，貌似不能超过 600
     * @return 最终图片地址
     */
    public static String getImgUrl(String imgUrl, int width, int height) {
        if (width > 800) {

        } else if (height > 600) {

        }
        return "http://tnfs.tngou.net/image" + imgUrl + "_" + width + "x" + height;
    }
}
