package com.demo;

import sun.misc.Launcher;

import java.net.URL;
import java.util.stream.Stream;

/**
 * @author linhua
 * @date 2020/11/8
 * @description
 */
public class ClassLoaderTest extends ClassLoader{

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }



    public static void main(String[] args) {

        //获取系统类加载器
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        System.out.println(loader); //sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取上层
        ClassLoader parent = loader.getParent();
        System.out.println(parent);  //sun.misc.Launcher$ExtClassLoader@12bb4df8

        //试图获取上层，获取不到引导类加载器
        ClassLoader bost = parent.getParent();
        System.out.println(bost);    //null

        //对于用户自定义来说，获取他的类加载器,默认使用系统类加载器加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);  //sun.misc.Launcher$AppClassLoader@18b4aac2

        //对于系统核心api类加载，证明了string 类是引导类加载器加载
        //可以推到java 核心类库都是引导类加载器
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);   //null

        //ClassLoader自己是用的引导类加载
        ClassLoader classLoader2 = Launcher.class.getClassLoader();
        System.out.println(classLoader2);   //null

        //Launcher 它是一个java虚拟机的入口应用
        System.out.println("*********启动类加载器************");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        Stream.of(urLs).forEach(System.out::println);

        // 从上面路径中，随意选择一个类，来看看他的类加载器是什么：得到的是null，说明是  根加载器


    }
}
