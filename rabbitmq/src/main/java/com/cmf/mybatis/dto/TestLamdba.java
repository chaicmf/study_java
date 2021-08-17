package com.cmf.mybatis.dto;


import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * @author chaiminfang
 * @date 2021/8/9
 */
public class TestLamdba {

    public static <T> T mergeThings(T a, T b, BiFunction<T,T,T> merge){

        return merge.apply(a,b);
    }

    public static String appendStrings(String a,String b){
        return a+b;
    }

    public String appendString2(String a,String b){
        return a+b;
    }



    public static void main(String[] args) {


        TestLamdba testLamdba=new TestLamdba();
        //使用lambda表达式调用方法mergeThings
        System.out.println(TestLamdba.mergeThings("hello","world",(a,b)->a+b));


        //引用静态方法
        System.out.println(TestLamdba.mergeThings("hello","world",TestLamdba::appendStrings));


        //引用特定对象的示例方法
        System.out.println(TestLamdba.mergeThings("hello","world",testLamdba::appendString2));


        //应用任意对象的示例方法
        System.out.println(TestLamdba.mergeThings("hello","world",String::concat));




        /***
         * stream() 为集合创建串行流
         * parallelStream，为集合创建并行流，是流并行处理程序的代替方法
         * forEach() Stream提供新的方法来迭代流中的每个数据
         * map(),方法用于映射每个元素到对应的结果。map(i -> i*i)集合中的每个元素变为平方
         * filter() 方法用法语通过设置的条件过滤出元素，filter(string->string.isEmpty())过滤出空字符串
         * limit() 方法用于获取指定数量的流，limit(10)获取10条数据
         * sorted(),方法用于对流进行排序
         * collect(Collectors.toList),用于返回列表或字符串，Collectors.joining(",")  将集合转换成逗号隔开的字符串
         */


        //foreach()
        List<String> strings = Arrays.asList("a","b","c");
        strings.stream().forEach(System.out::println);


        //  map()   distinct() 去重
        List<Integer> numbers=Arrays.asList(1,8,4);
        List<Integer> collect = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());

        collect.stream().forEach(System.out::println);


        //filter()
        List<String> strAry=Arrays.asList("asf","sdf","gjd","ksi","kdu");
        long a = strAry.stream()
                .filter(string -> string.contains("a"))
                .count();
        System.out.println(a);


        //limit() 用于获取指定数量的流
        Random random=new Random();
        random.ints().limit(5).forEach(System.out::println);



        //sorted 用于对流的排序，
        numbers.stream().sorted().forEach(System.out::println);

        //collectors 实现了会多规约操作，例如将流转换为集合和聚合元素，返回列表或者字符串
        //collectors 是专门用来作为Stream.collect方法中的参数的
        System.out.println(strAry.stream().collect(Collectors.joining(",")));


        String  str="ksi";

        String s1 = strAry.stream()
                .filter(s -> str.equals(s))
                .findFirst()
                .orElse(null);
        System.out.println(s1);



    }
}
