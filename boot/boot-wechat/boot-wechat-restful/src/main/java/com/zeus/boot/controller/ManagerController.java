package com.zeus.boot.controller;

import com.zeus.boot.entity.Admin;
import com.zeus.boot.repo.AdminRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(value = "Web Api", tags = {"web后台管理系统"})
@RestController
@RequestMapping(path = "manager")
public class ManagerController {

    @Autowired
    private AdminRepository adminRepository;

    @ApiOperation(value = "管理员登陆接口")
    @ApiResponses({ @ApiResponse(code = 200, message = "操作成功"),
            @ApiResponse(code = 500, message = "服务器内部异常")})
    @PostMapping(path = "login")
    Map<String, Object> login(String username, String password){

        Admin admin = adminRepository.getOne(username);

        Map<String, Object> result = new HashMap<>();
        if(password.equals(admin.getPassword())){
            result.put("username", admin.getUsername());
            result.put("password", admin.getPassword());
            result.put("type", null);
        }
        return result;
    }
}
