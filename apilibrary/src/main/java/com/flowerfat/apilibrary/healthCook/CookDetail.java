package com.flowerfat.apilibrary.healthCook;

/**
 * Created by 明明大美女 on 2015/10/20.
 */
public class CookDetail {

    private int id;
    private String name;
    private String img;
    private String tag;
    private String food;
    private String message;
    private String count;
    private String fcount;
    private String rcount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFcount() {
        return fcount;
    }

    public void setFcount(String fcount) {
        this.fcount = fcount;
    }

    public String getRcount() {
        return rcount;
    }

    public void setRcount(String rcount) {
        this.rcount = rcount;
    }

    @Override
    public String toString() {
        return "CookDetail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", tag='" + tag + '\'' +
                ", food='" + food + '\'' +
                ", message='" + message + '\'' +
                ", count='" + count + '\'' +
                ", fcount='" + fcount + '\'' +
                ", rcount='" + rcount + '\'' +
                '}';
    }
}
