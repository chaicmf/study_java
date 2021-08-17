package com.cmf.mybatis.service;

import com.cmf.mybatis.pojo.TestDto;

import java.util.List;

/**
 * @author chaiminfang
 * @date 2021/8/9
 */
public interface TestService {

    List<TestDto>  selectList(Integer pageNum, Integer pageSize);

}
