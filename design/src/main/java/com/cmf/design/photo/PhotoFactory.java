package com.cmf.design.photo;

public class PhotoFactory  implements Photo{
    @Override
     public    Object   getBean(String className){
         try {
             Class<PhotoType> aClass  = (Class<PhotoType>) Class.forName(className);
             return aClass.newInstance();
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (InstantiationException e) {
             e.printStackTrace();
         }
         return null;


     }
}
