package com.cmf.design.entities;

import org.springframework.stereotype.Component;

@Component
public class ProductFactory   implements ProductProduct {

    @Override
    public void mothed() {
        Class<Product> productClass = Product.class;
        System.out.println("创建产品工厂"+productClass.getName());
    }
}
