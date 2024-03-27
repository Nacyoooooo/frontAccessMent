package com.chenzhihao.frontassessment.Model

import lombok.Data

/**
 * 职位类型
 */
@Data
class PositionType internal constructor(var id:Number,//职位id
                                        var positionClassName:String,//职位名称
                                        var positionNumber: Number){//发布时间

}