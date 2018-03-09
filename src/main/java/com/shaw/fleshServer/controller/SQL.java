package com.shaw.fleshServer.controller;

public class SQL {
    @Override
    public String toString() {
        return "DROP TABLE IF EXISTS `tbl_user`;" +
                "CREATE TABLE `tbl_user` (\n" +
                "  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',\n" +
                "  `user_name` varchar(60) NOT NULL COMMENT '用户名',\n" +
                "  `password` varchar(100) NOT NULL COMMENT '密码',\n" +
                "  `is_vip` varchar(1) NOT NULL COMMENT '是否vip用户(0:否,1:是)',\n" +
                "  PRIMARY KEY (`user_id`),\n" +
                "  UNIQUE KEY `user_name` (`user_name`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;" +
                "  CREATE TABLE `tbl_video` (\n" +
                "  `vedio_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '视频id',\n" +
                "  `vedio_name` varchar(100) NOT NULL COMMENT '视频名',\n" +
                "  `vedio_path` varchar(250) NOT NULL COMMENT '存储路径',\n" +
                "  `vedio_class` varchar(20) DEFAULT NULL COMMENT '视频分类()',\n" +
                "  PRIMARY KEY (`vedio_id`),\n" +
                "  UNIQUE KEY `vedio_class` (`vedio_class`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
    }
}
