package com.chenzhihao.frontassessment.Service.impl

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.date.DateTime
import cn.hutool.core.io.FileUtil
import cn.hutool.core.io.file.FileReader
import cn.hutool.core.io.file.FileWriter
import cn.hutool.json.JSONUtil
import com.chenzhihao.frontassessment.Common.Results
import com.chenzhihao.frontassessment.Common.fail
import com.chenzhihao.frontassessment.Common.ok
import com.chenzhihao.frontassessment.Model.Concurrent_USER
import com.chenzhihao.frontassessment.Model.UNEXIST_USER
import com.chenzhihao.frontassessment.Model.Users
import com.chenzhihao.frontassessment.Service.UsersService
import com.chenzhihao.frontassessment.Util.createToken
import com.chenzhihao.frontassessment.Util.getNumber
import com.chenzhihao.frontassessment.Util.getPassword
import com.chenzhihao.frontassessment.dto.LoginDto
import com.chenzhihao.frontassessment.dto.MessageDto
import com.chenzhihao.frontassessment.dto.RegisterDto
import com.chenzhihao.frontassessment.dto.TokenDto
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import java.io.File
import java.util.concurrent.ConcurrentLinkedQueue

@Service
class UsersServiceImpl:UsersService, InitializingBean {
    lateinit var  Filess: File
    var userList=ConcurrentLinkedQueue<Users>()
    private val lock=Any()

    override fun message(token: TokenDto): Results<Any> {
        token.token.let {
            val message = isLogin(it)
            if(message.equals(UNEXIST_USER))return fail(msg = "token校验失败")
            else if (message.equals(Concurrent_USER))return fail(msg = "高并发异常")
            val target=Users()
            BeanUtil.copyProperties(message,target)
            target.password=null
            return ok(data = target)
        }
    }

    override fun login(loginInfo: LoginDto): Results<Any> {
        loginInfo.let {data->
            userList.forEach {
                if (it.number.equals(data.number)){
                    if (it.password.equals(data.password)){
                        return ok(data= createToken(data.number,data.password))
                    }
                }
            }
        }
        return fail(msg = "账号或密码不正确（可能已改密码，请重登）")
    }

    override fun register(registerInfo: RegisterDto): Results<Any> {
        registerInfo.let {data->
            if(!data.password.equals(data.repassword))return fail(msg = "两次密码不一致！")
            Filess.listFiles()?.forEach {
                if (it.nameWithoutExtension.equals(data.number))return fail(msg = "该账号已被注册！")
            }
            val newFile = FileUtil.touch(Filess, data.number + ".txt")
            var newUsers=Users(data.number,data.password)
            newUsers.date=DateTime().toString("yyyy-MM-dd")
            FileWriter(newFile).write(JSONUtil.toJsonStr(newUsers))
            userList.add(newUsers)
        }
        return ok(data = "注册成功！")
    }

    override fun messageChange(message: MessageDto): Results<Any> {
        val login = isLogin(message.token)
        if(login.equals(UNEXIST_USER))return fail()
        else if (login.equals(Concurrent_USER))return fail(msg = "高并发异常")
        message.data.let {msgs->
            //number不可变
            var number=login.number
            var password=login.password
            msgs.password?.let {
                password=msgs.password
            }
            val loginMap = BeanUtil.beanToMap(login)
            val msgsMap = BeanUtil.beanToMap(msgs)
            val combinedMap = loginMap+ msgsMap
            val newUser = BeanUtil.fillBeanWithMap(combinedMap, Users(), false)
            newUser.number=number
            newUser.password=password
            Filess.listFiles()?.forEach { file ->
                if (file.nameWithoutExtension.equals(number)){
                    FileWriter(file).write(JSONUtil.toJsonStr(newUser))
                    userList.remove(login)
                    userList.add(newUser)
                    val show=Users()
                    BeanUtil.copyProperties(newUser,show)
                    show.password=null
                    return ok(data = show, msg = "修改成功")
                }
            }
        }
        return fail()
    }

    override fun afterPropertiesSet() {
        println(File("").absolutePath)
        val absolutePath = File("").absolutePath
        val UsersRoot = absolutePath + "\\Users"
        val file = File(UsersRoot)
        if (file.exists()){
            Filess=file
            Filess.let {
                val listFiles = it.listFiles()
                listFiles?.forEach {
                    val json = FileReader(it).readString()
                    if (JSONUtil.isTypeJSON(json)){
                        val user = JSONUtil.toBean(json, Users::class.java)
                        userList.add(user)
                    }
                }
            }
        }else{
            file.mkdirs()
            Filess=file
        }
    }
    fun isLogin(token:String):Users{
        val number = getNumber(token)
        val password = getPassword(token)
        userList.forEach {
            if (it.number.equals(number)){
                if (it.password.equals(password)){
                    return it
                }
            }
//                return Concurrent_USER
        }
        return UNEXIST_USER
    }
}