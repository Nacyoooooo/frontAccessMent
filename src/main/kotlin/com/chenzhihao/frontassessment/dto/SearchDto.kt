package com.chenzhihao.frontassessment.dto

import lombok.Data

/**
 * 职位搜索的dto
 */
@Data
class SearchDto internal constructor(val Keyword:String,//职位关键词
                                     val type:String,//职位类型
                                     val place:String//工作地点
){
}
class SearchMsgDto internal constructor(val id:String){

}