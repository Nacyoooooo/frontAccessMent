package com.chenzhihao.frontassessment.Controller

import com.chenzhihao.frontassessment.Common.Results
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 职位相关的接口
 */
@Controller
@RequestMapping("/position")
class PositionController {
    /**
     * 获取职位类型
     */
    @GetMapping
    @ResponseBody
    fun position(): Results<Any> {
        return Results.ok(data = "test")
    }

    /**
     * 获取职位列表
     */
    @PostMapping("/search")
    @ResponseBody
    fun search(): Results<Any> {
        return Results.ok(data = "test")
    }

    /**
     * 职位页面详细信息
     */
    @PostMapping("/message")
    @ResponseBody
    fun message(): Results<Any> {
        return Results.ok(data = "test")
    }
}