package com.shaw.fleshServer;

import com.shaw.fleshServer.controller.SQL;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@MapperScan("com.shaw.fleshServer.mapper")
public class FleshServerApplication extends SpringBootServletInitializer {

    static {
//        executeSql();
    }

    public static void main(String[] args) {
        /**
         *  没有执行？？？？
         */
        System.out.println("FleshServer main ++");
        SpringApplication.run(FleshServerApplication.class, args);
        System.out.println("FleshServer main --");
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FleshServerApplication.class);
    }

//    public static void executeSql() {
//        Connection connection = null;
//        try {
//            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/flesh", "root", "");
//            ScriptRunner runner = new ScriptRunner(connection);
//            runner.setAutoCommit(false);
//            runner.setSendFullScript(true);
//
//            runner.runScript(new StringReader(new SQL().toString()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//    }
}
