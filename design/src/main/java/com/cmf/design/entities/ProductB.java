package com.cmf.design.entities;

public class ProductB  implements ProductProduct{
    public  void mothedB(){
        System.out.println("这是B产品的方法");
    }


    @Override
    public void mothed() {
        System.out.println("接口公共方法");
    }
}
