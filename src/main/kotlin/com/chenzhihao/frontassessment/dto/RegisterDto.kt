package com.chenzhihao.frontassessment.dto

import lombok.Data

/**
 * 注册dto
 */
@Data
class RegisterDto internal constructor(val number:String,//账号
                                       val password:String,//密码
                                       val repassword:String//第二次确认密码
){
}