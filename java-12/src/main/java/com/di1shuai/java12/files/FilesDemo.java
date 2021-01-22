package com.di1shuai.java12.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Shea
 * @date 2021-01-22
 * @description 文件比较 mismatch 返回第一个不匹配的位置，如果都匹配就返回-1L
 */
public class FilesDemo {

    public static void main(String[] args) {
        Path path1 = Paths.get("/Users/shuai/Documents/GitRepo/mine/language/java-versions/java-12/src/main/java/com/di1shuai/java12/files/FilesDemo.java");
        Path path2 = Paths.get("/Users/shuai/Documents/GitRepo/mine/language/java-versions/java-12/src/main/java/com/di1shuai/java12/files/FilesDemo.java");

        try {
            long fileMismatch = Files.mismatch(path1, path2);
            System.out.println(fileMismatch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
