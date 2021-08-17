package com.cmf.design;

import com.cmf.design.prototypePattern.PrototypePattern;
import com.cmf.design.prototypePattern.PrototypePattern2;
import org.junit.Test;

public class PrototypePatternTest {
    @Test
    public  void  getPrototypePattern(){
        PrototypePattern prototypePattern=new PrototypePattern();
        prototypePattern.setString("13");
        PrototypePattern prototypePattern1=prototypePattern.clone1();
        System.out.println(prototypePattern.clone1()==prototypePattern);
    }
    @Test
    public  void  getPrototypePattern2(){
        PrototypePattern2  prototypePattern=new PrototypePattern2();
        prototypePattern.setData("12345677889898");
        prototypePattern.setName("name");
        System.out.println("date==="+prototypePattern.getData());
        System.out.println("name==="+prototypePattern.getName());

        PrototypePattern2  prototypePattern2=prototypePattern.clone2();
        prototypePattern2.setName("name2");
        System.out.println("date==="+prototypePattern2.getData());
        System.out.println("name==="+prototypePattern2.getName());


        System.out.println("false=="+(prototypePattern2==prototypePattern));
        System.out.println("true=="+(prototypePattern2.getData()==prototypePattern.getData()));
        System.out.println("true=="+(prototypePattern2.getName()==prototypePattern.getName()));
    }


    @Test
    public  void  getTest() throws Exception {
        try{
//            if(!false){
//                throw  new  Exception("cuowwwww");
//            }
            int b=1/0;
        }catch (Exception e){
            throw  new  Exception("cuowww2121212ww");
        }

    }
}
