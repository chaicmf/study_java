package com.cmf.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserBuilder {
    private String name;
    private String sex;
    private int age;
}
