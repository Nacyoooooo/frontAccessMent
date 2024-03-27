package com.chenzhihao.frontassessment

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import com.chenzhihao.frontassessment.Common.Results
@SpringBootTest
class ResultsTest {
    @Test
    fun testResult(){
        val ok=Results.ok("123", msg = "123")
        println(ok)
        val fail=Results.fail(msg = "666")
        println(fail)
    }
}