package com.shaw.fleshServer.controller;

import com.shaw.fleshServer.base.common.FleshResult;
import com.shaw.fleshServer.entity.TblUser;
import com.shaw.fleshServer.service.TblUserService;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/api")
public class TblUserController {

//    static {
//        exceSql();
//    }

    @Autowired
    private TblUserService tblUserService;

    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public FleshResult getUserById(@RequestParam int userId) {
        TblUser tblUser = tblUserService.getUserById(userId);
        return new FleshResult("0", tblUser);
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public FleshResult addTblUser(@RequestBody TblUser tblUser) {
        FleshResult fleshResult = new FleshResult();
        try {
            tblUserService.addUser(tblUser);
        } catch (Exception e) {
            return new FleshResult("1", e.getMessage());
        }
        fleshResult.setCode("0");
        return fleshResult;
    }

//    public static void exceSql() {
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
