package com.di1shuai.java19.recordcase;

public record Point(int x, int y) {


    public static void main(String[] args) {
        Point point = new Point(1, 2);
        System.out.println(point);
        System.out.println(point.x());
        System.out.println(point.y());

    }

}
