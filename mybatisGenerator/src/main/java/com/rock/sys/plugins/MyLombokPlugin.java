package com.rock.sys.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * mybatis -gennerator 集成lombok的插件
 * @author chaiminfang
 * @date 2021/8/10
 */
public class MyLombokPlugin  extends PluginAdapter {

    private final String IGNORE_FIELDS = "ignoreFields";
    private final String MY_SUPPER_CLASS = "supperClass";
    private final String GENERATOR_DEFAULT_SERIAL_VERSION_UID = "defaultSerialVersionUID";

    private String supperClass;

    @Override
    public boolean validate(List<String> warnings) {
        boolean valid = true;

        try {
            supperClass = properties.getProperty(MY_SUPPER_CLASS);

            Class.forName(supperClass);
        } catch (ClassNotFoundException e) {
            warnings.add(getString("ValidationError.18","MyLombokPlugin","MY_SUPPER_CLASS"));
            valid=false;
        }
        return valid;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //添加domain的import
        topLevelClass.addImportedType("lombok.Data");
        //无参构造函数
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        //全参构造函数
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        //equals和hashCode方法
        topLevelClass.addImportedType("lombok.EqualsAndHashCode");

        //添加domain的注释
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        topLevelClass.addAnnotation("@EqualsAndHashCode(callSuper = false)");


        //添加domain的类注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * ("+introspectedTable.getFullyQualifiedTable()+")实体类");
        topLevelClass.addJavaDocLine(" * @author chaiminfang");
        topLevelClass.addJavaDocLine(" * @date " + date2Str(new Date()));
        topLevelClass.addJavaDocLine(" */");

        //设置父类
//        if (supperClass != null) {
//            topLevelClass.setSuperClass(new FullyQualifiedJavaType(supperClass));
//        }

        //设置 GENERATOR_DEFAULT_SERIAL_VERSION_UID  Serializable 的serialVersionUID
        String generatorDefaultSerialVersionUID = properties.getProperty(GENERATOR_DEFAULT_SERIAL_VERSION_UID);
        if("true".equals(generatorDefaultSerialVersionUID)){
            generatorDefaultSerialVersionUID(topLevelClass);
        }


        //将需要忽略生成的属性过滤掉
        List<Field> fields=topLevelClass.getFields();
            String ignoreFields=getIgnoreFields();
         if(null!= ignoreFields && !"".equals(ignoreFields)){
             String[] field = ignoreFields.split(",");
             for (String ignoreField : field) {
                 System.out.println(ignoreField);
                 for (Field field1 : fields) {
                     if(ignoreField.equalsIgnoreCase(field1.getName())){
                         fields.remove(field1);
                     }
                 }
             }
         }

         //给每个字段加注释
        for (Field field : fields) {
            StringBuilder fieldSb = new StringBuilder();
            field.addJavaDocLine("/**");
            fieldSb.append(" * ");
            String fieldName = field.getName(); // java字段名是驼峰的，需要转成下划线分割
            String underlineFieldName = camelToUnderline(fieldName);
            IntrospectedColumn introspectedColumn = introspectedTable.getColumn(underlineFieldName);
            if (null != introspectedColumn) {
                fieldSb.append(introspectedColumn.getRemarks());
            }
            field.addJavaDocLine(fieldSb.toString().replace("\n", " "));
            field.addJavaDocLine(" */");

        }

        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
                                   IntrospectedTable introspectedTable) {
        // Mapper文件的注释
//        interfaze.addJavaDocLine("/**");
//        interfaze.addJavaDocLine("* Created by Mybatis Generator on " + date2Str(new Date()));
//        interfaze.addJavaDocLine("*/");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 不生成getter
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
                                              IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 不生成setter
        return false;
    }


    private static String camelToUnderline(String fieldName){
        StringBuilder result = new StringBuilder();
        if(fieldName !=null && fieldName.length() > 0){
            result.append(fieldName.substring(0,1).toUpperCase());

            //循环处理其余字符
            for (int i = 1; i < fieldName.length(); i++) {
                String s = fieldName.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    private String getIgnoreFields(){
        return properties.getProperty(IGNORE_FIELDS);
    }

    private String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 生成 Serializable 的serialVersionUID
     *
     */

    private void generatorDefaultSerialVersionUID(TopLevelClass topLevelClass){
        Field field=new Field();
         field.setFinal(true);
         field.setInitializationString("1L");
         field.setName("serialVersionUID");
         field.setStatic(true);
         field.setType(new FullyQualifiedJavaType("long"));
         field.setVisibility(JavaVisibility.PRIVATE);
         topLevelClass.getFields().add(0,field);
    }
}
