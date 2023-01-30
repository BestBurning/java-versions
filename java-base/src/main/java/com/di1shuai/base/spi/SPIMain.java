package com.di1shuai.base.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author shea
 * @since 2023/1/30
 */
public class SPIMain {

    public static void main(String[] args) {
        ServiceLoader<Search> s = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = s.iterator();
        while (iterator.hasNext()) {
            Search search = iterator.next();
            search.searchDoc("hello world");
        }

    }
}
