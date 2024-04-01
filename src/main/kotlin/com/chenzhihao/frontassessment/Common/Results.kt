package com.chenzhihao.frontassessment.Common

import com.chenzhihao.frontassessment.Model.PositionDetailedInfo
import lombok.Data
@Data
class Results<T> internal constructor(val status: Int,
                                      val msg: String,
                                      val data: T) {
}
val SUCCESS = 200
val FAIL = 500
fun ok(data: Any= SUCCESS, msg: String="操作成功", status: Int= SUCCESS): Results<Any> {
    return Results(status, msg, data)
}
fun fail(msg: String="操作失败", status: Int= FAIL, data: Any= FAIL): Results<Any> {
    return Results(status, msg, data)
}