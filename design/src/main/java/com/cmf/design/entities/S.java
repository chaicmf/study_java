package com.cmf.design.entities;
import java.util.Random;
public class S {
    //static final 的常量，值不会变化，引用时不会触发类的初始化
   static final  int Init=50;
   //虽然被static final修饰了但是intit22不是常量调用时类会被初始化
   static  final  int  inti22=new Random().nextInt(100);
    static {
        System.out.println("sSSSSSssss");
    }
}
 class A{
    //未被final修饰，可能是变量，引用时会触发类的初始化
    static   int  init2=55;
    static {
        System.out.println("AAAAAAAAAA");
    }

}
class T{
    public static void main(String[] args) {
        //不会触发初始化  staticfinal是编译期静态常量，是不会触发类的初始化
        Class<S> sClass = S.class;
        System.out.println("1111111111111111");
        //不会触发类的初始化
        System.out.println(S.Init);
        //会触发类的初始化
        System.out.println(S.inti22);
        //会触发类的初始化
        System.out.println(A.init2);
    }
}
