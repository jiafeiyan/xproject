package com.zeus.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeController {

    @GetMapping(value = "/getCelebrity")
    public String getCelebrityLists(){
        return "hhhhhhh";
    }
}
