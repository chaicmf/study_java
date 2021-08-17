package com.cmf.design.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLUtils {

    public static  Object getInstance(String name){

        try {
            DocumentBuilderFactory builderFactory=DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document parse = documentBuilder.parse(new File("src/main/resources/config.xml"));
            NodeList className = parse.getElementsByTagName(name);
            Node item = className.item(0);
            String firstChild = item.getFirstChild().getNodeValue().trim();

            Class c= Class.forName(firstChild);
            Object object = c.newInstance();
            return object;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("找不到文件");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println("读取xml节点失败");
        } catch (InstantiationException e) {
            e.printStackTrace();
            System.out.println("创建实例失败");
        }

        return null;
    }
}
