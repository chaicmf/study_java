package com.cmf.design.prototypePattern;

/**
 * 运行模式实现cloneable接口
 * 使用obejct（充当原型模型）的 clone原型方法
 */
public class PrototypePattern2  implements   Cloneable{
    private String name;
    private  String data;

    public PrototypePattern2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public  PrototypePattern2 clone2(){
        Object object = null;
        try {
            object=super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (PrototypePattern2) object;
    }
}
