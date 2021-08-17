package com.cmf.design.SingletonPattern;

import java.security.PrivateKey;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Sunny软件公司承接了一个服务器负载均衡(Load Balance)软件的开发工作，
 * 该软件运行在一台负载均衡服务器上，可以将并发访问和数据流量分发到服务器集群中的多台设备上进行并发处理，
 * 提高系统的整体处理能力，缩短响应时间。由于集群中的服务器需要动态删减，且客户端请求需要统一分发，
 * 因此需要确保负载均衡器的唯一性，只能有一个负载均衡器来负责服务器的管理和请求的分发，
 * 否则将会带来服务器状态的不一致以及请求分配冲突等问题。如何确保负载均衡器的唯一性是该软件成功的关键。
 *   Sunny公司开发人员通过分析和权衡，决定使用单例模式来设计该负载均衡器
 */
public class SingletonPattern {
    //private volatile static SingletonPattern singletonPattern=null;
    private List<String> serverList=null;

    private SingletonPattern(){
        serverList=new ArrayList<>();
    }


    /**
     * 由于静态单例对象没有singleton的成员变量直接实例化，因此类加载时
     * 不会实例化Singleton，第一次调用getInstance() 将加载内部类HoldClass，在改内部定义
     * 一个static的变量singletonPattern,此时会首先初始化成员变量，Java虚拟机来保证线程
     * 安全性，确保改成员变量之恩呢初始化一次，由于instance()方法没有任何锁定，一次其性能
     * 不饿能造成影响
     *
     * 通过使用IoDH，我么既可以实现延迟加载，又可以保证线程安全，不影响系统性能，

     */
    private  static   class  SingletonPatterns{
        private final static SingletonPattern singletonPattern=new SingletonPattern();

    }
    public static  SingletonPattern instance(){

        return SingletonPatterns.singletonPattern;
    }

   /* *//**
     *  共有静态成员方法，创建实例
     *   假如在某一瞬间线程A和线程B都在调用getInstance()方法，此时instance对象为null值，
     *   均能通过instance == null的判断。由于实现了synchronized加锁机制，
     *   线程A进入synchronized锁定的代码中执行实例创建代码，线程B处于排队等待状态，
     *   必须等待线程A执行完毕后才可以进入synchronized锁定代码。但当A执行完毕时，
     *   线程B并不知道实例已经创建，将继续创建新的实例，导致产生多个单例对象，违背单例模式的设计思想，
     *   因此需要进行进一步改进，在synchronized中再进行一次(instance == null)判断，
     *   这种方式称为双重检查锁定(Double-Check Locking)。
     *   如果使用双重检查锁定来实现懒汉式单例类，需要在静态成员变量instance之前增加修饰符volatile
     *   被volatile修饰的成员变量可以确保多个线程都能够正确处理，
     *
     *//*
    public static  SingletonPattern instance(){
        if(singletonPattern==null){
            synchronized(SingletonPattern.class) {
                if(singletonPattern==null) {
                    singletonPattern = new SingletonPattern();
                }
            }
        }
        return singletonPattern;
    }*/
    /**
     * 添加server
     * @param server
     */
    public   void addServer(String server){
        serverList.add(server);
    }

    /**
     * 删除server
     * @param server
     */
    public  void removeServer(String server){
        serverList.remove(server);
    }

    /**
     * 随机返回server
     * @return
     */
    public  String getServer(){
        Random random=new Random();
        int i = random.nextInt(serverList.size());
        return serverList.get(i);
    }

    /**
     *    虽然创建了四个LoadBalancer对象，但是它们实际上是同一个对象，
     *    因此，通过使用单例模式可以确保LoadBalancer对象的唯一性。
     * @param args
     */
    public static void main(String[] args) {
        SingletonPattern s1, s2, s3, s4;
        s1=SingletonPattern.instance();
        s2=SingletonPattern.instance();
        s3=SingletonPattern.instance();
        s4=SingletonPattern.instance();

        if(s1==s2 && s2==s3 && s3==s4){
            System.out.println("创建了唯一实例");
        }
        s1.addServer("server s1");
        s2.addServer("server s2");
        s3.addServer("server s3");
        s4.addServer("server s4");

        for (int i = 0; i < 10; i++) {
            System.out.println("分发的服务器"+s1.getServer());
        }
    }
}
