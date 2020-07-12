package com.yao.memorandum.file;

import java.net.URL;

public class Path {

    public static void main(String[] args) {
        URL resource = Path.class.getResource("library.properties");
        System.out.println(resource.getPath());
        System.out.println();
    }
    public static URL getClassPath(String fileName){
        URL resource = Path.class.getClassLoader().getResource (fileName);
        return resource;
    }
}
