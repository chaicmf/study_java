package com.cmf.design.prototypePattern;

public class PrototypePattern {
    private  String string;

    public PrototypePattern() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public  PrototypePattern clone1(){
        PrototypePattern prototypePattern = new PrototypePattern();
        prototypePattern.setString(this.string);
        return prototypePattern;

    }
}
