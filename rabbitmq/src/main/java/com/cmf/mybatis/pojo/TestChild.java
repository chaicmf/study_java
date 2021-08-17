package com.cmf.mybatis.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TestChild {
    private String childId;

    private String parentId;

    private String childName;

}