package com.chenzhihao.frontassessment.dto

import lombok.Data

/**
 * 职位搜索的dto
 */
@Data
class SearchDto internal constructor(var Keyword:String?,//职位关键词
                                     var type:String?,//职位类型
                                     var place:String?//工作地点
)
val TYPES= arrayListOf("美术类","营销类","策划类","技术类","职能类","运营类","项管类","音频类","管理类")
class SearchMsgDto internal constructor(val id:String){

}