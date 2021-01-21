package com.diyishuai.java8.function;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Shea
 * @date 2021-01-21
 * @description
 */
public class FunctionReference {

    public static void main(String[] args) {
        classConstructorFunctionReference();
        classStaticFunctionReference();
        instanceFunctionReference();
    }


    /**
     * 类构造方法引用
     */
    private static void classConstructorFunctionReference() {
        System.out.println("类构造方法引用:");
        //构造引用
        // 0 无参构造
        Supplier<Apple> appleSupplier = Apple::new;
        Apple a0 = appleSupplier.get();
        System.out.println(a0);
        // 1 单参构造
        Function<String, Apple> appleFunction = Apple::new;
        Apple a1 = appleFunction.apply("1");
        System.out.println(a1);
        // 2 双参构造
        BiFunction<String, Double, Apple> appleBiFunction = Apple::new;
        Apple a2 = appleBiFunction.apply("2", 0.5);
        System.out.println(a2);
    }

    /**
     * 类静态方法引用
     */
    private static void classStaticFunctionReference() {
        System.out.println("类静态方法引用");
        List list = new ArrayList();

        list.add(new Apple("1"));
        list.add(new Apple("2"));
        list.add(new Apple("3"));
        list.add(new Apple[]{new Apple("4"), new Apple("5")});

        list.stream().forEach(Show::show);

    }


    /**
     * 实例方法引用
     */
    private static void instanceFunctionReference() {
        System.out.println("实例方法引用");
        List<Apple> list = new ArrayList();

        list.add(new Apple("1"));
        list.add(new Apple("2"));
        list.add(new Apple("3"));

        list.stream().map(a -> a.getId()).forEach(System.out::println);
        System.out.println("----");
        list.stream().map(Apple::getId).forEach(System.out::println);
    }


}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Apple {

    private String id;
    private double weight;
    private String color;

    public Apple(String id) {
        this.id = id;
    }

    public Apple(String id, double weight) {
        this.id = id;
        this.weight = weight;
    }

    public void show(){
        System.out.println(this.toString());
    }

}

class Show {

    public static void show(Object obj) {
        if (obj instanceof List) {
            for (Object o : (List) obj) {
                System.out.print(o.toString() + "\t");
            }
            System.out.println();
        } else if (obj instanceof Object[]) {
            for (Object o : (Object[]) obj) {
                System.out.print(o.toString() + "\t");
            }
            System.out.println();
        } else {
            System.out.println(obj.toString());
        }
    }

}