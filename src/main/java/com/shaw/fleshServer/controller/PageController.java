package com.shaw.fleshServer.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class PageController {

    @RequestMapping("/")
    public String toIndex(){
        return "index";
    }
}
