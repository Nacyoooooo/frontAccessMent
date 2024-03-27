package com.chenzhihao.frontassessment.Model

import lombok.Data

/**
 * 用户类
 */
@Data
class Users internal constructor(var name:String,//姓名
                                 var postbox:String,//邮箱
                                 var phone:String,//手机号
                                 var date:String,//出生年月
                                 var gender:String,//性别
                                 var education:String,//学历
                                 var location:String,//所在地
                                 var idNumber:String,//证件号码
                                 var work:String,//工作经验
){
}