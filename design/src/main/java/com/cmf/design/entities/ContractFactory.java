package com.cmf.design.entities;

import org.springframework.stereotype.Component;

@Component
public class ContractFactory implements  ProductProduct {
    @Override
    public void mothed() {
        Class<ProductB> productBClass = ProductB.class;
        System.out.println("创建合同工厂"+productBClass.getName());
    }
}
