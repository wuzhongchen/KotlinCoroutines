package com.demo.kotlin05;

import java.util.Objects;

/**
 * @author ningchuanqi
 * @description
 */
public class Employee {

    private String name;
    private String idCardNo;

    public Employee(String name, String idCardNo) {
        this.name = name;
        this.idCardNo = idCardNo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(idCardNo, employee.idCardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, idCardNo);
    }
}
