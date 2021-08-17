package com.cmf.design;
import com.cmf.design.constants.Day;
import com.cmf.design.entities.*;
import com.cmf.design.AbstractFactory.factory.AndroidFactory;
import com.cmf.design.AbstractFactory.InterfaceController;
import com.cmf.design.photo.Photo;
import com.cmf.design.utils.XMLUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DesignApplicationTests {

    @Test
    public void contextLoads() {



            ProductProduct productProduct ;
            productProduct = (ProductProduct) XMLUtils.getInstance("className");
            productProduct.mothed();

    }

    @Test
    public  void  enumx(){
       Day day=Day.THURSDAY;
       day.getDay(day);
    }

    @Test
    public  void  getCla(){
        try {
            Class<?> aClass = Class.forName("com.cmf.design.entities.ProductA");
            ProductA p=new ProductA();
            Class aClass1 = p.getClass();
            Class<ProductA> productAClass = ProductA.class;
            System.out.println(productAClass.getName());
            System.out.println(aClass.getName());
            System.out.println(aClass1.getName());


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void  classs(){

        Class c=int.class;
        c=double.class;
        Class<? extends Number> a=Integer.class;

    }

    @Test
    public  void equ(){
        String  str1="abc";
        String str2="abc";
        System.out.println("str1.equals(str2)="+str1.equals(str2));
        System.out.println("str1.equalsIgnoreCase(str2)="+str1.equalsIgnoreCase(str2));


    }

    @Test
    public void instance(){
        ProductA a =new ProductA();
        System.out.println(a instanceof Product);
        System.out.println(Product.class.isInstance(a));
    }


@Test
    public  void  photo(){
    Photo photos = (Photo) XMLUtils.getInstance("photo");
    ProductProduct p = (ProductProduct) photos.getBean("com.cmf.design.entities.ProductB");
    p.mothed();
}

    @Test
    public void Phone(){
        AndroidFactory factory = (AndroidFactory) XMLUtils.getInstance("AbstractFactory");
        InterfaceController interfaceController = factory.createInterfaceController();
        interfaceController.interfaces();
    }

}
