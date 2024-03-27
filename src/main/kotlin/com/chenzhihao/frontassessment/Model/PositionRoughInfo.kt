package com.chenzhihao.frontassessment.Model

import lombok.Data

/**
 * 职位信息（粗略版）
 */
@Data
class PositionRoughInfo internal constructor(var id:Number,//职位id
                                             var positionName:String,//职位名称
                                             var positionTime:String){//发布时间
}