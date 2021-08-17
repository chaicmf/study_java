package com.cmf.design.entities;

public class ProductA  extends  Product implements  ProductProduct{
    public  void mothedA(){
        System.out.println("这是a产品的方法");
    }



    @Override
    public void mothed() {
        System.out.println("A接口方法");
    }

    @Override
    public void getM() {
        System.out.println("A");
    }
}
