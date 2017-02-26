package com.szl.mybatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * Created by zsc on 2017/2/26.
 */
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class})})
public class MyPlugin implements Interceptor{
    Properties props = null;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("before...");
        Object obj = invocation.proceed();
        System.out.println("after...");

        return obj;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("调用");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties props) {
        System.out.println(props.get("dbType"));
        this.props = props;
    }
}
