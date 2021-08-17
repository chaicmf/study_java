package com.cmf.mybatis.mapper;

import com.cmf.mybatis.pojo.Test;
import com.cmf.mybatis.pojo.TestChild;
import com.cmf.mybatis.pojo.TestDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestMapper {

    List<TestDto> selectList(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    List<TestChild> selectChild(String testId);
    int deleteByPrimaryKey(String testId);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(String testId);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}