package com.chenzhihao.frontassessment.Model

import lombok.Data

/**
 * 用户类
 */
@Data
class Users internal constructor(var name:String,
                                 var postbox:String,
                                 var phone:String,
                                 var date:String,
                                 var gender:String,
                                 var education:String,
                                 var location:String,
                                 var idNumber:String,
                                 var work:String,
){
}