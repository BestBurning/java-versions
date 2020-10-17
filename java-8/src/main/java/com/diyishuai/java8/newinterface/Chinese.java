package com.diyishuai.java8.newinterface;

public class Chinese extends Human implements Animal {
    @Override
    public void run() {
        System.out.println("my run");
    }

    public static void main(String[] args) {
        Chinese c = new Chinese();
        c.run();
        c.jump();
        Animal.eat();
    }
}
