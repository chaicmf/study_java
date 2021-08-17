package com.cmf.springboot;


import com.cmf.springboot.entity.ExtendUser;

import java.util.ArrayList;
import java.util.List;

public class Test {

    @org.junit.Test
    public  void jtest(){
        ExtendUser extendUser=ExtendUser.builder().name("a").birthday("20190909").age(2).sex("男").build();
        System.out.println(extendUser.toString());
    }

    @org.junit.Test
    public  void  atest(){
        String a="1";
        List<String>  string=new ArrayList<>();
        boolean  b=false;
        System.out.println(!bb.not);
        if (a == null) {

        }
    }
}
