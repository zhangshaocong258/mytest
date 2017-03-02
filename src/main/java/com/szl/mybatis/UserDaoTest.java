package com.szl.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

/**
 * Created by zsc on 2016/12/18.
 */
public class UserDaoTest {
    public static void main(String args[]) {
        UserDaoTest userDaoTest = new UserDaoTest();
//        userDaoTest.select();
//        userDaoTest.selectAll();
        userDaoTest.insert();
//        userDaoTest.update();
//        userDaoTest.remove();

    }
    public void select() {
        SqlSession sqlSession = getSessionFactory().openSession();
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        User user = userMapper.selectUserById(7);
        System.out.println(user.getUserAddress());
    }

    public void selectAll() {
        SqlSession sqlSession = getSessionFactory().openSession();
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        List<User> users = userMapper.selectAllUsers();
        System.out.println(users.get(0).getId());
    }

    public void insert() {
        SqlSession sqlSession = getSessionFactory().openSession();
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        User u = new User(2, "ddaa", "11", "faff");//xml中设置是否自动编号
//        u.setUserName("zsc");
//        u.setUserAge("18");
//        u.setUserAddress("uuu");
        userMapper.insertUser(u);
        sqlSession.commit();
    }

    public void update() {
        SqlSession sqlSession = getSessionFactory().openSession();
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        User u = userMapper.selectUserById(7);
//        u.setUserName("zsc");
        u.setUserAge("8");
//        u.setUserAddress("UESTC");
        userMapper.updateUser(u);
        sqlSession.commit();
    }

    public void remove() {
        SqlSession sqlSession = getSessionFactory().openSession();
        UserDao userMapper = sqlSession.getMapper(UserDao.class);
        userMapper.deleteUser(1);
        sqlSession.commit();
    }

    //Mybatis 通过SqlSessionFactory获取SqlSession, 然后才能通过SqlSession与数据库进行交互
    private static SqlSessionFactory getSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "configuration.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(resource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
