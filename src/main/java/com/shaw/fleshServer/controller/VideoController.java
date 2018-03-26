package com.shaw.fleshServer.controller;

import com.shaw.fleshServer.base.common.FleshResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

@RestController
@RequestMapping("/api")
public class VideoController {

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public FleshResult isVip(HttpServletRequest request, HttpServletResponse response, @RequestParam String web, @RequestParam(value = "0") int index,
                             @RequestParam(value = "50") int length) {
        String path = request.getContextPath();
        File f = new File(path,"v33a.json");
        return new FleshResult("0","");
    }
}
