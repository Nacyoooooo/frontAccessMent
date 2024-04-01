package com.chenzhihao.frontassessment.Config

import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Common.fail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class ExceptionHolder {
    @ExceptionHandler(Exception::class) //异常处理器
    @ResponseBody //返回json数据
    fun error(e: Exception): Results<Any> {
        e.printStackTrace()
        //统一返回服务器异常信息
        return fail(msg = "服务器异常")
    }
}