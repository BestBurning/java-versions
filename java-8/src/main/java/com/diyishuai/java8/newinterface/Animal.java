package com.diyishuai.java8.newinterface;

public interface Animal {
    void run();

    default void jump(){
        System.out.println(this.getClass().getSimpleName()+ " jump");
    }

    static void eat(){
        System.out.println(Animal.class.getSimpleName()+" eat");
    }
}
