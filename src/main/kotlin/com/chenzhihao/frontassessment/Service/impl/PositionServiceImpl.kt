package com.chenzhihao.frontassessment.Service.impl

import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Service.PositionService
import com.chenzhihao.frontassessment.dto.SearchDto
import com.chenzhihao.frontassessment.dto.SearchMsgDto
import org.springframework.stereotype.Service

@Service
class PositionServiceImpl:PositionService {
    override fun position(): Results<Any> {
        return Results.ok()
    }

    override fun search(searchInfo: SearchDto): Results<Any> {
        return Results.ok()
    }

    override fun message(searchInfo: SearchMsgDto): Results<Any> {
        return Results.ok()
    }
}