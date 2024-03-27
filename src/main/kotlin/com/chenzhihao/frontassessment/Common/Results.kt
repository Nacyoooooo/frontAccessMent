package com.chenzhihao.frontassessment.Common

import lombok.Data
@Data
class Results<T> internal constructor(val status: Int,
                                      val msg: String,
                                      val data: T) {
    companion object{
        val SUCCESS = 200
        val FAIL = 500
        fun ok(data: Any= SUCCESS, msg: String="操作成功", status: Int= SUCCESS): Results<Any> {
            return Results(status, msg, data)
        }
        fun fail(msg: String="操作失败",status: Int= FAIL,data: Any= FAIL): Results<Any> {
            return Results(status, msg, data)
        }
    }
}