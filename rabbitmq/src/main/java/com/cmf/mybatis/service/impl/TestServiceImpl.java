package com.cmf.mybatis.service.impl;

import com.cmf.mybatis.mapper.TestMapper;
import com.cmf.mybatis.pojo.TestDto;
import com.cmf.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chaiminfang
 * @date 2021/8/9
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public List<TestDto> selectList(Integer pageNum, Integer pageSize) {
        return testMapper.selectList(pageNum,pageSize);
    }
}
