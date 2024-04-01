package com.chenzhihao.frontassessment.Service

import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.dto.SearchDto
import com.chenzhihao.frontassessment.dto.SearchMsgDto
import org.springframework.stereotype.Service

@Service
interface PositionService {
    fun position(): Results<Any>
    fun search(searchInfo: SearchDto?): Results<Any>
    fun message(searchInfo: SearchMsgDto?): Results<Any>
}