package com.demo.kotlin05;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ningchuanqi
 * @description
 */
public class EqualsHashCodeTest {

    public static void main(String[] args) {
        //从内存角度来看，这是两个对象
        //业务上来看，这是同一个人
        Employee e1 = new Employee("Jack", "0239023232");
        Employee e2 = new Employee("Jack", "0239023232");

        //往哈希表结构里面添加元素，先比较对象的哈希值，再用equals比较
        //如果都是true，认为是同一个对象
        Set<Employee> set = new HashSet<>();
        set.add(e1);
        set.add(e2);

        //当两个对象equals为true时，hashCode值也应该相等
        System.out.println(set);

    }

}
