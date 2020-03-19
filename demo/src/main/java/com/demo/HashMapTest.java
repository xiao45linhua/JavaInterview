package com.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author linhua
 * @apiNote
 * @date 2020-03-19 2:41 下午
 */

public class HashMapTest {
    public static void main(String[] args) {


        HashMap<String, Integer> map = new HashMap<>(16,1);
        Integer previous = map.put("test1", 1);
        System.out.println(previous);
        System.out.println("\n");
        previous = map.put("test2",2);
        previous = map.put("test3",3);
        previous = map.put("test4",4);previous = map.put("test5",5);
        previous = map.put("test6",6);
        previous = map.put("test7",7);
        previous = map.put("test8",8);
        previous = map.put("test9",9);previous = map.put("test10",10);
        previous = map.put("test11",11);previous = map.put("test12",12);
        previous = map.put("test13",13);
        previous = map.put("test14",14);
        previous = map.put("test15",14);
        previous = map.put("test16",14);
        System.out.println("previous".hashCode());
        System.out.println("\n");
        Integer result = map.get("test1");
        System.out.println(tableSizeFor(123));

    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= 1024) ? 16 : n + 1;
    }

}
