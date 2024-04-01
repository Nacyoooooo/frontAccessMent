package com.chenzhihao.frontassessment

import cn.hutool.core.io.file.FileReader
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Common.fail
import com.chenzhihao.frontassessment.Common.ok
import com.chenzhihao.frontassessment.Util.read
import com.chenzhihao.frontassessment.Util.readFileLines
import org.springframework.core.io.ClassPathResource


class ResultsTest {
    @Test
    fun testResult(){
        val ok=ok("123", msg = "123")
        println(ok)
        val fail=fail(msg = "666")
        println(fail)
    }
    @Test
    fun testFileUtils(){
        println(read("test.txt"))
    }

    @Test
    fun testPosition(){
        val readFileLines = readFileLines("positions.txt")
        readFileLines.forEach{
            println(it)

        }
    }
}