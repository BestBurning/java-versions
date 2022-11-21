package com.di1shuai.java19.memorycase;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SequenceLayout;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;
import static java.lang.foreign.MemoryLayout.PathElement;

public class Jep424Demo {

    public static void main(String[] args) {
        new Jep424Demo().memoryOperation();
    }

    public void memoryOperation() {
        /*
         * 1. 创建结构化的顺序内存布局，结构如下
         * struct Point {
         * int x;
         * int y;
         * } pts[10];
         */
        SequenceLayout ptsLayout = MemoryLayout.sequenceLayout(10, MemoryLayout.structLayout(
                JAVA_INT.withName("x"),
                JAVA_INT.withName("y")));

        // 2. 分配内存并对内存设置值
        VarHandle xHandle = ptsLayout.varHandle(PathElement.sequenceElement(), PathElement.groupElement("x"));
        VarHandle yHandle = ptsLayout.varHandle(PathElement.sequenceElement(), PathElement.groupElement("y"));
        MemorySegment segment = MemorySegment.allocateNative(ptsLayout, MemorySession.openImplicit());
        for (int i = 0; i < ptsLayout.elementCount(); i++) {
            xHandle.set(segment,/* index */ (long) i, /* value to write */i); // x
            yHandle.set(segment,/* index */ (long) i, /* value to write */i); // y
            System.out.printf("index => %d, x = %d, y = %d\n", i, i, i);
        }

        // 3. 获取内存值
        int xValue = (int) xHandle.get(segment, 3);
        System.out.println("Point[3].x = " + xValue);
        int yValue = (int) yHandle.get(segment, 6);
        System.out.println("Point[6].y = " + yValue);
    }
}
