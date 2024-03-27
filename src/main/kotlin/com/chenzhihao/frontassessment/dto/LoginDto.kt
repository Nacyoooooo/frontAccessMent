package com.chenzhihao.frontassessment.dto

import lombok.Data
import lombok.ToString

@Data
@ToString
class LoginDto internal constructor(val number:String,//账号
                                    val password:String//密码
){
}