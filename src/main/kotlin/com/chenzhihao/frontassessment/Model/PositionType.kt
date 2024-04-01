package com.chenzhihao.frontassessment.Model

import lombok.Data

/**
 * 职位类型
 */
@Data
class PositionType internal constructor(var id:Int,//职位id
                                        var positionClassName:String,//职位名称
                                        var positionNumber: Int){//职位数量

}