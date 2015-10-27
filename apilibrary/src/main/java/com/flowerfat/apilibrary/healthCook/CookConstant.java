package com.flowerfat.apilibrary.healthCook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 明明大美女 on 2015/10/22.
 *
 *
 */
public class CookConstant {

    List<Big> categoryList = new ArrayList<>();

    public String[] categoryName =
            {"请选择", "美容养颜", "减肥瘦身", "保健养生", "适宜人群", "餐食时节", "孕产哺乳",
                    "女性养生", "男性养生", "心脏血管", "皮肤器官", "肠胃消化", "口腔呼吸", "肌肉神经", "癌症其他"};

    public String[] getNameArray (int index){
        return categoryList.get(index).getSmallNames();
    }

    public void initData() {
        Big category;
        // id=1
        category = new Big(1, "美容养颜",
                new String[]{"美容", "养颜", "排毒", "美白", "抗皱", "祛斑", "黑眼圈", "润肤", "保湿", "护眼"},
                new int[]{20, 21, 22, 23, 24, 25, 26, 27, 28, 29});
        categoryList.add(category);
        // id=2
        category = new Big(2, "减肥瘦身",
                new String[]{"瘦身", "减肥", "瘦脸", "瘦腿", "瘦腰", "瘦臀", "丰胸"},
                new int[]{30, 31, 32, 33, 34, 35, 36});
        categoryList.add(category);

        // 内容有点多。。。。 手打好累
//        // id=3
//        category = new Big(3, "保健养生",
//                new String[]{"瘦身", "减肥", "瘦脸", "瘦腿", "瘦腰", "瘦臀", "丰胸"},
//                new int[]{30, 31, 32, 33, 34, 35, 36});
//        categoryList.add(category);
//        // id=4
//        category = new Big(4, "适宜人群",
//                new String[]{"宝宝", "女性", "孕妇", "产妇", "考生", "白领", "婴儿", "老年人"},
//                new int[]{73, 74, 75, 76, 77, 78, 79, 80, 81});
//        // id=5
//        category = new Big(5, "餐食时节",
//                new String[]{"春天", "夏天", "秋天", "冬天", "节时", "早餐", "晚餐", "夜宵", "睡前", "空腹"},
//                new int[]{82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92});
//        // id=6
//        category = new Big(6, "餐食时节",
//                new String[]{"春天", "夏天", "秋天", "冬天", "节时", "早餐", "晚餐", "夜宵", "睡前", "空腹"},
//                new int[]{82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92});
//        categoryList.add(category);

    }

    /**
     * 最顶层的类，包含了14个
     */
    public class Big {
        int id;
        String name;
        String[] smallNames;
        int[] smallIds;

        private Big(int id, String name, String[] sNames, int[] sIds) {
            this.id = id;
            this.name = name;
            this.smallNames = sNames;
            this.smallIds = sIds;
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

        public String[] getSmallNames() {
            return smallNames;
        }

        public void setSmallNames(String[] smallNames) {
            this.smallNames = smallNames;
        }

        public int[] getSmallIds() {
            return smallIds;
        }

        public void setSmallIds(int[] smallIds) {
            this.smallIds = smallIds;
        }
    }

}
