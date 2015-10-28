package com.flowerfat.apilibrary.healthCook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 明明大美女 on 2015/10/22.
 *
 *
 */
public class CookConstant {

    List<Big> categoryList = new ArrayList<>();

    public static String[] categoryName =
            {"美容养颜", "减肥瘦身", "保健养生", "适宜人群", "餐食时节", "孕产哺乳",
                    "女性养生", "男性养生", "心脏血管", "皮肤器官", "肠胃消化", "口腔呼吸", "肌肉神经", "癌症其他"};

    public String[] getNameArray (int index){
        return categoryList.get(index).getChildrenName();
    }

    public void initData() {

    }

    /**
     * 最顶层的类，包含了14个
     */
    public class Big {
        int id;
        String name;
        String[] childrenName;
        int[] childrenId;

        public Big(){

        }

        public Big(int id, String name, String[] sNames, int[] sIds) {
            this.id = id;
            this.name = name;
            this.childrenName = sNames;
            this.childrenId = sIds;
        }

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

        public String[] getChildrenName() {
            return childrenName;
        }

        public void setChildrenName(String[] childrenName) {
            this.childrenName = childrenName;
        }

        public int[] getChildrenId() {
            return childrenId;
        }

        public void setChildrenId(int[] childrenId) {
            this.childrenId = childrenId;
        }

        @Override
        public String toString() {
            return "Big{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", smallNames=" + Arrays.toString(childrenName) +
                    ", smallIds=" + Arrays.toString(childrenId) +
                    '}';
        }
    }

}
