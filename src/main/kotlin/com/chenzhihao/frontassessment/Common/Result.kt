package com.chenzhihao.frontassessment.Common

import lombok.Data
@Data
public class Result<T> internal constructor(val status: Int,
                                            val msg: String,
                                            val data: T) {
    companion object{
        val SUCCESS = 200
        val FAIL = 500
        fun ok(data: Any=Any(), msg: String="操作成功",status: Int= SUCCESS): Result<Any> {5
            return Result(status, msg, data)
        }
        fun fail(msg: String="操作失败",status: Int= FAIL,data: Any="null"): Result<Any> {
            return Result(status, msg, data)
        }
    }
}