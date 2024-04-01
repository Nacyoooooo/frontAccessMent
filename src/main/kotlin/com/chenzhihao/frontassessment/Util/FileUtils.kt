package com.chenzhihao.frontassessment.Util

import cn.hutool.core.io.IoUtil
import cn.hutool.core.io.file.FileReader
import org.springframework.core.io.ClassPathResource
import java.io.File
import java.io.InputStream
import java.nio.charset.Charset

fun read(name:String): String? {
    var re=ClassPathResource(name)
    var f=FileReader(re.path)
    return f.readString()
}

fun readFileLines(name: String): List<String> {
    val re=ClassPathResource(name)
    val inputStream = re.inputStream
    val readLines = IoUtil.readLines(inputStream, Charset.forName("utf-8"), ArrayList<String>())
    return readLines
}

fun getLines(inputStream:InputStream): ArrayList<String>? {
    val readLines = IoUtil.readLines(inputStream, Charset.forName("utf-8"), ArrayList<String>())
    return readLines
}