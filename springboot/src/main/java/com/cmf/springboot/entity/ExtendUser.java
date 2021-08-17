package com.cmf.springboot.entity;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class ExtendUser extends UserBuilder {
    private String  birthday;

    @Builder
    public ExtendUser(String name, String sex, int age, String birthday) {
        super(name, sex, age);
        this.birthday = birthday;
    }
}
