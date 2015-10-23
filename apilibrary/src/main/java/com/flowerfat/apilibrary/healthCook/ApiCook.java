package com.flowerfat.apilibrary.healthCook;

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

    private final String byNameUrl = "http://a.apix.cn/yi18/cook/search";
    private final String categoryUrl = "http://a.apix.cn/yi18/cook/cookclass";
    private final String byIdUrl = "http://a.apix.cn/yi18/cook/show";
    private final String listUrl = "http://a.apix.cn/yi18/cook/list";

    private final Gson mGson = new Gson();

    public ApiCook() {
    }

    /**
     * 默认的获得列表
     * @param id
     * @return
     */
    public String getList(int id){
        return getList(id, 1, 20);
    }
    /**
     * 获得菜谱列表，三个参数均 非必须
     * type 为排序方式，id或count 一个是按最新，一个是按访问数
     *
     * @param id   分类ID 默认0
     * @param page 页数 默认1
     * @param limit 每页条数 默认20
     * @return
     */
    public String getList(int id, int page, int limit) {
        String httpArg = "id=" + id + "&page=" + page + "&limit=" + limit+"&type="+"count";
        String jsonResult = Http.getCookX(listUrl, httpArg);
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
        String httpArg = "keyword=" + URLEncoder.encode(name);
        String jsonResult = Http.getCookX(byNameUrl, httpArg);
        System.out.println(jsonResult);
        return jsonResult;
    }

    /**
     * 通过菜的ID，获得该菜的做法
     *
     * @param id
     * @return
     */
    public String getById(int id) {
        String httpArg = "id=" + id;
        String jsonResult = Http.getCookX(byIdUrl, httpArg);
        return jsonResult;
    }

    /**
     * 获得菜谱分类
     *
     * @param page 非必须，默认为0
     * @return
     */
    public String getCategory(int page) {
        String httpArg = "id=" + page;
        String jsonResult = Http.getCookX(categoryUrl, httpArg);
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
    public List<CookDetail> getListByName(String name) {
        String httpArg = "name=" + URLEncoder.encode(name);
        String jsonResult = Http.request(byNameUrl, httpArg);
        try {
            return mGson.fromJson(jsonResult, new TypeToken<List<CookDetail>>(){}.getType());
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
        return "http://www.yi18.net/" + imgUrl;
    }

}
