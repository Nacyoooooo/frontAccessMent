package com.chenzhihao.frontassessment.Model

import lombok.Data

/**
 * 职位信息(详细版)
 */
@Data
class PositionDetailedInfo internal constructor(var positionName:String,var positionDescribe:String,var place:String,var time:String){
}