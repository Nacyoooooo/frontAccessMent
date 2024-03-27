package com.chenzhihao.frontassessment.Service.impl

import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Service.UsersService
import com.chenzhihao.frontassessment.dto.LoginDto
import com.chenzhihao.frontassessment.dto.RegisterDto
import com.chenzhihao.frontassessment.dto.TokenDto
import org.springframework.stereotype.Service

@Service
class UsersServiceImpl:UsersService {
    override fun message(token: TokenDto): Results<Any> {
        return Results.ok()
    }

    override fun login(loginInfo: LoginDto): Results<Any> {
        return Results.ok()    }

    override fun register(registerInfo: RegisterDto): Results<Any> {
        return Results.ok()    }
}