package com.chenzhihao.frontassessment.Controller

import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Service.PositionService
import com.chenzhihao.frontassessment.dto.SearchDto
import com.chenzhihao.frontassessment.dto.SearchMsgDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * 职位相关的接口
 */
@Controller
@RequestMapping("/position")
class PositionController {
    @Autowired
    lateinit var positionService:PositionService
    /**
     * 获取职位类型
     */
    @GetMapping
    @ResponseBody
    fun position(): Results<Any> {
        return positionService.position()
    }

    /**
     * 获取职位列表
     */
    @PostMapping("/search")
    @ResponseBody
    fun search(@RequestBody searchInfo:SearchDto): Results<Any> {
        return positionService.search(searchInfo)
    }

    /**
     * 职位页面详细信息
     */
    @PostMapping("/message")
    @ResponseBody
    fun message(@RequestBody searchInfo: SearchMsgDto): Results<Any> {
        return positionService.message(searchInfo)
    }
}