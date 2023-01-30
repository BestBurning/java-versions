package com.di1shuai.base.spi;

import java.util.List;

/**
 * @author shea
 * @since 2023/1/30
 */
public class FileSearchImpl implements Search{
    @Override
    public List<String> searchDoc(String keywords) {
        System.out.println("文件搜索");
        return null;
    }
}
