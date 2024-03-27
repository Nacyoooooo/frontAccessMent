package com.chenzhihao.frontassessment.Model

class Users internal constructor(val name:String,val age:Int){
    override fun toString(): String {
        return "Users(name='$name', age=$age)"
    }
}