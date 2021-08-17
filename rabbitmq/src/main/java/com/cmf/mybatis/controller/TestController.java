package com.cmf.mybatis.controller;

import com.cmf.mybatis.pojo.TestDto;
import com.cmf.mybatis.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chaiminfang
 * @date 2021/8/9
 */
@Api(tags = "测试")
@Validated
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
     private TestService testService;

    @ApiOperation(value = "商户号列表", response = TestDto.class)
    @PostMapping(value = "list")
    public Object orderList(
            @ApiParam("页码")
            @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
            @ApiParam("条数")
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") Integer pageSize
    ){
        System.out.println(testService.selectList(pageNum, pageSize).toString());
        return  testService.selectList(pageNum, pageSize);
    }

}
