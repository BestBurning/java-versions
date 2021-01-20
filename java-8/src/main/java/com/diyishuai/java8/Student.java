package com.diyishuai.java8;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Shea
 * @date 2021-01-20
 * @description
 */
@Data
@Accessors(chain = true)
public class Student {

    private String id;

    private String name;

}
