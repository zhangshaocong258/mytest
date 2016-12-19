package com.szl;

import org.apache.log4j.Logger;

/**
 * Created by zsc on 2016/12/19.
 */
public class log4jTest {
    private static Logger logger = Logger.getLogger(log4jTest.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        // System.out.println("This is println message.");

        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }
}
