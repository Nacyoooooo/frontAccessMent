package com.chenzhihao.frontassessment.Model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

/**
 * 用户类
 */
@Data
class Users internal constructor(var name:String?,//姓名
                                 var postbox:String?,//邮箱
                                 var phone:String?,//手机号
                                 var date:String?,//出生年月
                                 var gender:String?,//性别
                                 var education:String?,//学历
                                 var location:String?,//所在地
                                 var idNumber:String?,//证件号码
                                 var work:String?,//工作经验
                                 var number:String?,//账号
    var password:String?
){
    constructor() : this(null,null,null,null,null,null,null,null,null,null,null)
    constructor(number: String,password: String?):this(null,null,null,null,null,null,null,null,null,number,password)

    override fun equals(other: Any?): Boolean {
        other?.let {
            if(it is Users){
                this.number?.equals(it.number)?:false
            }
        }
        return this === other
    }
}
val UNEXIST_USER=Users()
val Concurrent_USER=Users()