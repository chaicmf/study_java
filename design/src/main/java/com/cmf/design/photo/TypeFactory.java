package com.cmf.design.photo;

import com.cmf.design.constants.ProductType;
import com.cmf.design.entities.ProductA;
import com.cmf.design.entities.ProductB;
import com.cmf.design.entities.ProductProduct;

public class TypeFactory implements Photo {
    @Override
    public Object getBean(String name) {
        try {
            Class<?> aClass =  Class.forName(name);
            return aClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Object getBeans(String name) {

        ProductProduct productProduct = null;
        if (name.equalsIgnoreCase(ProductType.PRODUCT_A.getType())) {
            productProduct = new ProductA();
        }
        if (name.equalsIgnoreCase(ProductType.PRODUCT_B.getType())) {
            productProduct = new ProductB();
        }

        return productProduct;
    }
}
