package com.zeus.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeController {

    @RequestMapping(value = "/getCelebrity", method = {RequestMethod.GET})
    public String getCelebrityLists(){
        int a = 1/0;
        return "hhhhhhh";
    }
}
