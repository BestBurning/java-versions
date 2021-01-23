package com.di1shuai.java15.sealed;

/**
 * @author Shea
 * @date 2021-01-23
 * @description
 * 封闭类 sealed class
 * permits 可以允许三种类型的子类进行继承
 * 1. final 类
 * 2. non-sealed 不封闭类
 * 3. sealed 类
 *
 */
public sealed class SealedDemo
        permits FinalClass, NonSealedClass, SealedClass {

}

final class FinalClass extends SealedDemo {

}

non-sealed class NonSealedClass extends SealedDemo {

}

sealed class SealedClass extends SealedDemo permits SealedClass.SubSealed {

    non-sealed class SubSealed extends SealedClass {

    }
}

