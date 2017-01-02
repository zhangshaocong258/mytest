package com.szl.mybatis;

/**
 * Created by zsc on 2016/12/18.
 * 如果有带参数的构造器
 * 必须有一个空的构造函数
 */
public class User {

    private int id;
    private String userName;
    private String userAge;
    private String userAddress;

    public User() {

    }

    public User(int id, String userName, String userAge, String userAddress) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
        this.userAddress = userAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
