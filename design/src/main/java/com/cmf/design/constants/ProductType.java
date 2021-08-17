package com.cmf.design.constants;

public enum ProductType {


    PRODUCT_A("A","产品A"),
    PRODUCT_B("B","产品B");

    private String type;
    private String desc;
       ProductType(String type, String desc) {
        this.type=type;
        this.desc=desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
