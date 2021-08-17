package com.cmf.design.photo;

public class jpg implements PhotoType {
    @Override
    public void write() {
        System.out.println("jpg");
    }
}
