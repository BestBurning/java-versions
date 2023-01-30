package com.di1shuai.base.spi;

import java.util.List;

/**
 * @author shea
 * @since 2023/1/30
 */
public interface Search {

    /**
     * 搜索接口
     * @param keywords
     * @return
     */
    List<String> searchDoc(String keywords);

}
