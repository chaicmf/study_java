package com.cmf.solr.SolrTest;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SolrTest {

    //查询数据
    @Test
    public  void  addDocument() throws IOException, SolrServerException {
        //创建一个solrServer对象，创建一个链接  ，参数为solr服务的url
        SolrServer   solrServer=new HttpSolrServer("http://192.168.0.22:8080/solr/collection1");
        //创建一个solrDocument文档对象
        SolrInputDocument document=new SolrInputDocument();
        //向文档对象中添加域，文档中必须包含一个id域,搜有域的名称必须在schema.xml中定义
        document.addField("id","3");
        document.addField("name","solr3");
        //把文档写入索引库
        solrServer.add(document);
        //提交
        solrServer.commit();
    }
    //删除数据
    @Test
    public  void deleteDocument() throws IOException, SolrServerException {
        //创建一个solrServer对象，创建一个链接  ，参数为solr服务的url
        SolrServer   solrServer=new HttpSolrServer("http://192.168.0.22:8080/solr/collection1");
        //按照文章的id进行删除
        //solrServer.deleteById("1");
        //根据查询删除
        solrServer.deleteByQuery("id:2");
         solrServer.commit();

    }
    // 查询数据
    @Test
    public  void  queryDocument() throws SolrServerException {
        //创建一个solrServer对象，创建一个对象，参数为solr服务的url
        SolrServer solrServer=new HttpSolrServer("http://192.168.0.22:8080/solr/collection1");
        //创建查询对象
        SolrQuery solrQuery=new SolrQuery();
        //设置需要查询的条件
        solrQuery.set("q","id:1 and name:solr3");
        //进行 solr的查询  接受查询的数据病将接受的内容展示出来
        QueryResponse  queryResponse=solrServer.query(solrQuery);
        SolrDocumentList results = queryResponse.getResults();
        //获取查询的记录数
        long numFound = results.getNumFound();
        System.out.println("一共有"+numFound+"条数据");
        for (SolrDocument solrdocument: results) {
            //使用get获取
           /* System.out.println(solrdocument);
            String id= (String) solrdocument.get("id");
           List<String> name= ( List<String>) solrdocument.get("name");
            System.out.println("id:"+id+"   name:"+name);*/
            //使用getFiledValue获取
            Object id = solrdocument.getFieldValue("id");
            Collection<Object> names=solrdocument.getFieldValues("name");
            System.out.println("id:"+id+"   name:"+names);
            //可以获取字段的名字
            Collection<String> fieldNames = solrdocument.getFieldNames();
            System.out.println("字段有"+fieldNames);
            //获取字段值的map集合
            Map<String, Object> fieldValueMap = solrdocument.getFieldValueMap();
            System.out.println("获取字段值的map集合:"+fieldValueMap);

        }

    }

}
