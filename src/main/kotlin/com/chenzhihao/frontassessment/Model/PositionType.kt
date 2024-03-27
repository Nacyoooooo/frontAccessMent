package com.chenzhihao.frontassessment.Model

import lombok.Data

/**
 * 职位类型
 */
@Data
class PositionType internal constructor(var id:Number,
                                        var positionClassName:String,
                                        var positionNumber: Number){

}