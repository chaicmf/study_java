package com.cmf.mybatis.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author chaiminfang
 * @date 2021/8/9
 */
@Data
@ToString
public class TestDto {
    private String testId;

    private String testName;

    private List<TestChild> list;
}
