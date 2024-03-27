package com.chenzhihao.frontassessment

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import com.chenzhihao.frontassessment.Common.Result
@SpringBootTest
class ResultTest {
    @Test
    fun testResult(){
        val ok=Result.ok("123", msg = "123")
        println(ok)
        val fail=Result.fail(msg = "666")
        println(fail)
    }
}