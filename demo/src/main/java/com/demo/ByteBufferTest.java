package com.demo;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author linhua
 * @date 2020/10/23
 * @description
 */
public class ByteBufferTest {

    private int b = 20;
    public static int a = 10;



    public static void main(String[] args) {
        System.out.println(ByteBufferTest.a);

        //Class.forName(), Class.forName().newInstance(), ClassLoader
//        System.out.println(a.getBytes());



//        ByteBuffer bb1 = ByteBuffer.allocate(12);
//        bb1.putInt(10);
//        bb1.put((byte) 20);
//        bb1.put((byte) 10);
//        bb1.put("linhua".getBytes());
//        System.out.println("Original ByteBuffer: " + Arrays.toString(bb1.array()));
//
//        System.out.println("\nposition:  "
//                + bb1.position());
//
//        System.out.println("\ncapacity:  "
//                + bb1.capacity());
//
//        ByteBuffer bb2 = bb1.slice();
//
//        System.out.println("\nshared subsequance ByteBuffer: "
//                + Arrays.toString(bb2.array()));
//
//        System.out.println("\nposition:  " + bb2.position());
//
//        System.out.println("\ncapacity:  " + bb2.capacity());
//
//        bb2.put((byte) 30);
//
//        System.out.println("updatebb2 ByteBuffer: " + Arrays.toString(bb2.array()));
//
//        System.out.println("updatebb1 ByteBuffer: " + Arrays.toString(bb1.array()));

    }
}
