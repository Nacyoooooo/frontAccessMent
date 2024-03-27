package com.chenzhihao.frontassessment.Controller

import com.chenzhihao.frontassessment.Model.Test
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.chenzhihao.frontassessment.Common.Results
@Controller
@RequestMapping("/test")
class TestController {
    /**
     * 测试链接
     */
    @RequestMapping
    @ResponseBody
    fun login(): Results<Any> {
        return Results.ok(data = "test")
    }
    @RequestMapping("/fail")
    @ResponseBody
    fun fail(@RequestBody test: Test) : Results<Any> {
        println(test)
        return Results.fail()
    }
}