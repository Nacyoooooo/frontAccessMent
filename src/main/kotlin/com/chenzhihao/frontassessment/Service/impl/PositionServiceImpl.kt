package com.chenzhihao.frontassessment.Service.impl

import cn.hutool.core.io.IoUtil
import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Common.fail
import com.chenzhihao.frontassessment.Common.ok
import com.chenzhihao.frontassessment.MainActivity
import com.chenzhihao.frontassessment.Model.*
import com.chenzhihao.frontassessment.Service.PositionService
import com.chenzhihao.frontassessment.Util.getLines
import com.chenzhihao.frontassessment.Util.readFileLines
import com.chenzhihao.frontassessment.dto.SearchDto
import com.chenzhihao.frontassessment.dto.SearchMsgDto
import com.chenzhihao.frontassessment.dto.TYPES
import org.springframework.beans.factory.InitializingBean
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.PathResource
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.stereotype.Service
import java.io.InputStream
import java.nio.charset.Charset
import kotlin.streams.toList


@Service
class PositionServiceImpl:PositionService,InitializingBean {
    var positionsTypes= ArrayList<PositionType>()
    var positionDatas= HashMap<Int, ArrayList<PositionDetailedInfo>>()
    var numbersList= intArrayOf(30, 27, 22, 18, 15, 12, 8, 5, 1)

    override fun position(): Results<Any> {
        return ok(data = positionsTypes, msg = "获取职位类型成功")
    }

    override fun search(searchInfo: SearchDto?): Results<Any> {
        //如果为null则传参异常，fail
        searchInfo?.let { se ->
            //如果不为null则找对应类型的list集合
            val dataList=se.type?.let { type ->
                TYPES.withIndex().firstOrNull { (index,data)-> type.equals(data) }?.index?.let { positionDatas.get(it+1) }
                //如果找不到对应d的type,则返回空集合
                    ?: emptyList<PositionDetailedInfo>()
            }//如果type为null,则把map全部传入
                ?:let {
                    positionDatas.values.flatMap { it }.toList()
                }
            val filter1=se.place?.let { place->
                dataList.stream().filter { it.place.equals(place) }.toList()
            }?:dataList
            val filter2=se.Keyword?.let { keyword->
                dataList.stream().filter {
                    it.positionName.contains(keyword)
                }.toList()
            }?:filter1
            val utlmate=filter2.let {
                it.map { Detailed2Rough(it) }
            }
            return ok(data = utlmate, msg = "获取职位列表成功")
        }
        return fail(data = emptyList<PositionRoughInfo>())
    }

    override fun message(searchInfo: SearchMsgDto?): Results<Any> {
        searchInfo?.let {
            positionDatas.values.forEach{
                it.forEach {
                    if (it.id == (searchInfo.id.toInt() ?: UNEXIST_ID))return ok(data = it, msg = "获取职位信息成功")
                }
            }
        }
        return fail(msg = "不存在此id！")
    }

    /**
     * 初始化service之后执行代码
     */
    override fun afterPropertiesSet() {
        var positionId=1
        val PositionsClasses= readFileLines("/types.txt")
        PositionsClasses.forEach {
            val data = it.split(",")
            val positionType = PositionType(data[2].toInt(), data[0], data[1].toInt())
            positionsTypes.add(positionType)
        }
        val filePath="positionss"
        var dataList=ArrayList<PositionDetailedInfo>()
        for (id in 1..9){
            val resolver: ResourcePatternResolver = PathMatchingResourcePatternResolver()
            val rePath=filePath+"/"+id
            for (index in (1..numbersList.get(id-1))){
                val rePathNew=rePath+"/"+index+".txt"
                val Lines = readFileLines(rePathNew)
                var Line=0;
                val Detailed=PositionDetailedInfo()
                val build=StringBuilder("")
                Lines.forEach {
                    if (it.equals("----"))Line++
                    else{
                        when(Line){
                            1->{Detailed.positionName=it}
                            2->{Detailed.time=it}
                            3->{Detailed.place=it}
                            4->{build.append(it+"\n")}
                        }
                    }
                }
                Detailed.positionDescribe=build.toString()
                Detailed.id=positionId
                dataList.add(Detailed)
                positionId++
            }
            positionDatas.put(id,dataList)
//            val resources = resolver.getResources(rePath)
//            resources.forEach {res->
//                println(res.exists())
//                val inputStream = res.inputStream
//                val fileNames = IoUtil.readLines(inputStream, Charset.forName("utf-8"), ArrayList<String>())
//                fileNames.forEach { fileName->
//                    val file = resolver.getResource(rePath+"/"+fileName)
//                    val LinesStream = file.inputStream
//                    val Lines=IoUtil.readLines(LinesStream, Charset.forName("utf-8"),ArrayList<String>())
//                    var Line=0;
//                    val Detailed=PositionDetailedInfo()
//                    val build=StringBuilder("")
//                    Lines.forEach {
//                        if (it.equals("----"))Line++
//                        else{
//                            when(Line){
//                                1->{Detailed.positionName=it}
//                                2->{Detailed.time=it}
//                                3->{Detailed.place=it}
//                                4->{build.append(it+"\n")}
//                            }
//                        }
//                    }
//                    Detailed.positionDescribe=build.toString()
//                    Detailed.id=positionId
//                    dataList.add(Detailed)
//                    positionId++
//                }
//                positionDatas.put(id,dataList)
//            }

//            listFiles.forEach {
//                val Lines = FileReader(it).readLines()
//                var Line=0;
//                val Detailed=PositionDetailedInfo()
//                val build=StringBuilder("")
//                Lines.forEach {
//                    if (it.equals("----"))Line++
//                    else{
//                        when(Line){
//                            1->{Detailed.positionName=it}
//                            2->{Detailed.time=it}
//                            3->{Detailed.place=it}
//                            4->{build.append(it+"\n")}
//                        }
//                    }
//                }
//                Detailed.positionDescribe=build.toString()
//                Detailed.id=positionId
//                dataList.add(Detailed)
//                positionId++
//            }
//            positionDatas.put(id,dataList)
        }
    }
}