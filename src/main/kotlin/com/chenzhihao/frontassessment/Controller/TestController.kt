package com.chenzhihao.frontassessment.Controller

import com.chenzhihao.frontassessment.Model.Test
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Common.fail
import com.chenzhihao.frontassessment.Common.ok
import com.chenzhihao.frontassessment.dto.TokenDto
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping

@Controller
@RequestMapping("/test")
class TestController {
    /**
     * 测试链接
     */
    @RequestMapping
    @ResponseBody
    @ApiOperation("测试")
    fun login(): Results<Any> {
        return ok(data = "test")
    }
    @RequestMapping("/fail")
    @ResponseBody
    @ApiOperation("测试")
    fun fail(@RequestBody test: Test) : Results<Any> {
        println(test)
        return fail()
    }
    @PostMapping("/token")
    @ResponseBody
    @ApiOperation("测试")
    fun token(@RequestBody token: TokenDto) : Results<Any> {
        println(token)
        return fail()
    }
}