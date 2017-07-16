package com.szl;

import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsc on 2016/5/23.
 * 重写hashcode和equal
 */

public class HashMapTest {

    public static void main(String[] args) {
        Employee emp = new Employee(2);

        emp.setName("Robin");

        // Put object in HashMap.
        Map<Employee, String> map = new HashMap<Employee, String>();
        map.put(emp, "Showbasky");

        System.out.println(map.get(emp));

        // Change Employee name. Change in 'name' has no effect
        // on hash code.
        emp.setAge("15");
        map.put(emp, "haha");
        System.out.println(map.get(emp));
        System.out.println(emp.getAge());
        for (Map.Entry<Employee, String> entry : map.entrySet()) {
            System.out.println("key= " + entry.getKey().getName() + " 结果 " +
                    entry.getKey().getAge() + " and value= " + entry.getValue());
        }

    }
}

class Employee {
    // It is specified while object creation.
    // Cannot be changed once object is created. No setter for this field.
    private int id;
    private String name;
    private String age = "";

    public Employee(final int id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    // Hash code depends only on 'id' which cannot be
    // changed once object is created. So hash code will not change
    // on object's state change
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (id != other.id)
            return false;
        return true;
    }
}