package com.cmf.mybatis.mapper;

import com.cmf.mybatis.pojo.TestChild;
import org.springframework.stereotype.Repository;

@Repository
public interface TestChildMapper {


    int deleteByPrimaryKey(String testId);

    int insert(TestChild record);

    int insertSelective(TestChild record);


    TestChild selectByPrimaryKey(String testId);



    int updateByPrimaryKeySelective(TestChild record);

    int updateByPrimaryKey(TestChild record);
}