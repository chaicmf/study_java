package com.rock.sys;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chaiminfang
 * @date 2021/8/10
 */
public class Test {
    public static void main(String[] args) {
        Test generator = new Test();
        System.out.println(System.getProperty("user.dir"));
        System.out.println(generator.getClass().getResource("/").getPath());
        generator.run();

    }


   public void run(){
       List<String> warnings = new ArrayList<String>();

       try {
           //true 生成的文件覆盖之前的
           boolean overwrite = true;
           //读取配置，构造Configuration
           //如果不想使用配置文件的话，也可以直接来new Configuration()然后给相应的属性赋值
           // File configFile = new File("E:\\workpace\\mybatisGenerator\\src\\main\\resources\\generatorConfig.xml");
           InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("generatorConfig.xml");
           ConfigurationParser cp=new ConfigurationParser(warnings);
           Configuration config=cp.parseConfiguration(resourceAsStream);
           DefaultShellCallback callback = new DefaultShellCallback(overwrite);
           MyBatisGenerator myBatisGenerator=new MyBatisGenerator(config,callback,warnings);
           myBatisGenerator.generate(null);
       } catch (IOException e) {
           e.printStackTrace();
       } catch (XMLParserException e) {
           e.printStackTrace();
       } catch (InvalidConfigurationException e) {
           e.printStackTrace();
       } catch (InterruptedException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       for (String s : warnings) {
           System.out.println(s);
       }
   }
}
