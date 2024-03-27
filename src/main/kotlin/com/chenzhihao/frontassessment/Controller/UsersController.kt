package com.chenzhihao.frontassessment.Controller

import com.chenzhihao.frontassessment.Common.Results
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/user")
class UsersController {
    /**
     * 注册
     */
    @PostMapping("/register")
    @ResponseBody
    fun register(): Results<Any> {
        return Results.ok(data = "test")
    }
    /**
     * 登录
     */
    @PostMapping("/login")
    @ResponseBody
    fun login(): Results<Any> {
        return Results.ok(data = "test")
    }
    /**
     * 获取个人信息
     */
    @PostMapping("/message")
    @ResponseBody
    fun message(): Results<Any> {
        return Results.ok(data = "test")
    }
}