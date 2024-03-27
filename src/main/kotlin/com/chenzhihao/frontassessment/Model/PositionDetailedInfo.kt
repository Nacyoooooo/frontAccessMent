package com.chenzhihao.frontassessment.Model

import lombok.Data

/**
 * 职位信息(详细版)
 */
@Data
class PositionDetailedInfo internal constructor(var positionName:String,//职位名称
                                                var positionDescribe:String,//职位描述
                                                var place:String,//工作地点
                                                var time:String){//发布时间
}