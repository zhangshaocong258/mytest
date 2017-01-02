package com.szl.mybatis;

import java.util.List;

/**
 * Created by zsc on 2016/12/18.
 * 有个函数名就行了
 */
public interface UserDao {
    public void insertUser(User user);

    public User selectUserById (int userId);

    public List<User> selectAllUsers();


    public void updateUser(User user);

    public void deleteUser(int Id);

}
