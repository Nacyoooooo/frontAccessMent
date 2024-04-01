package com.chenzhihao.frontassessment.Controller

import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Service.UsersService
import com.chenzhihao.frontassessment.dto.LoginDto
import com.chenzhihao.frontassessment.dto.MessageDto
import com.chenzhihao.frontassessment.dto.RegisterDto
import com.chenzhihao.frontassessment.dto.TokenDto
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/user")
class UsersController {
    @Autowired
    lateinit var usersService:UsersService
    /**
     * 注册
     */
    @PostMapping("/register")
    @ResponseBody
    @ApiOperation("注册")
    fun register(@RequestBody registerInfo:RegisterDto): Results<Any> {
        return usersService.register(registerInfo)
    }
    /**
     * 登录
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation("登录")
    fun login(@RequestBody loginInfo:LoginDto): Results<Any> {
        return usersService.login(loginInfo)
    }
    /**
     * 获取个人信息
     */
    @PostMapping("/message")
    @ResponseBody
    @ApiOperation("获取个人信息")
    fun message(@RequestBody token:TokenDto): Results<Any> {
        return usersService.message(token)
    }
    /**
     * 修改个人信息
     */
    @ApiOperation("修改个人信息")
    @PostMapping("/messageChange")
    @ResponseBody
    fun messageChange(@RequestBody token:MessageDto): Results<Any> {
        return usersService.messageChange(token)
    }
}