package com.chenzhihao.frontassessment.Service

import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.dto.LoginDto
import com.chenzhihao.frontassessment.dto.RegisterDto
import com.chenzhihao.frontassessment.dto.TokenDto
import org.springframework.stereotype.Service

@Service
interface UsersService {
    fun message(token: TokenDto): Results<Any>
    fun login(loginInfo: LoginDto): Results<Any>
    fun register(registerInfo: RegisterDto): Results<Any>
}
