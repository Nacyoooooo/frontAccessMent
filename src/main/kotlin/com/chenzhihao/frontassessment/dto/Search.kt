package com.chenzhihao.frontassessment.dto

import lombok.Data

@Data
class Search internal constructor(val Keyword:String,val type:String,val place:String){
}