package com.flowerfat.apifamily;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2015/10/22.
 */
public class Test {

    static List<String> inputData = new ArrayList<>();
    static List<String> outputData = new ArrayList<>();



    public static void main(String[] args){
//        initData();
//        doData();
//        for(String result : outputData){
            System.out.println("最终输出" + "result");
//        }
    }

    /**
     * 产生1000个0~1000的随机数，当作数据
     */
    private static void initData(){
        Random random = new Random();
        for (int i=0 ; i<10; i++){
            inputData.add("刘鹏sb"+random.nextInt(3));
        }
    }

    private static void doData(){
        while(inputData.size() != 0){
            Log.i("我是log", "输入数据的长度:"+inputData.size());
            inputData = doDataOneTime(inputData);
        }
    }


    /**
     * 处理数据，一个循环
     * @param _data
     */
    private static List<String> doDataOneTime(List<String> _data){
        // 创建一个临时List
        List<String> temporaryData = new ArrayList<>();
        // 获得第一个数据，并加入到最终List里
        String firstData = _data.get(0);
        outputData.add(firstData);
        // 数据的长度
        int length = _data.size();
        for(int i = 0; i< length; i++){
            if(!firstData.equals(_data.get(i))){
                temporaryData.add(_data.get(i));
            }
        }
        return temporaryData;
    }
}





