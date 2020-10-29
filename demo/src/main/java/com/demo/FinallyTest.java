package com.demo;

/**
 * @author linhua
 * @date 2020/10/16
 * @description
 */
public class FinallyTest {

    public static int getNumber(){
        try {
            int x = 0;
            return x;
        } catch (Exception e){
            return 9;
        } finally {
            return 10;
        }
    }

    public static void main(String[] args) {
        System.out.println(getNumber());
    }
}
