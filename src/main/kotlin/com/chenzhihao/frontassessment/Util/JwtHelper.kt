package com.chenzhihao.frontassessment.Util

import io.jsonwebtoken.*
import org.springframework.util.StringUtils
import java.security.Key
import java.util.*

val tokenExpiration=365*24*60*60*1000
val tokenSignKey:String = "MetaCodeMetaCodeMetaCodeMetaCodeMetaCodeMetaCodeMetaCodeMetaCodeMetaCodeMetaCodeMetaCodeMetaCode"

@Throws(JwtException::class)
fun createToken(number:String,password:String): String {
    return Jwts.builder()
        .setSubject(tokenSignKey)
        .setExpiration(Date(System.currentTimeMillis() + tokenExpiration))
        .claim("number", number)
        .claim("password", password)
        .signWith(SignatureAlgorithm.HS512, tokenSignKey)
        .compressWith(CompressionCodecs.GZIP)
        .compact()
}

@Throws(JwtException::class)
fun getNumber(token: String?): String? {
    if (StringUtils.isEmpty(token)) return null
    val claimsJws: Jws<Claims> =
        Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token)
    val claims = claimsJws.body
    return claims["number"] as String?
}
@Throws(JwtException::class)
fun getPassword(token: String?): String? {
    if (StringUtils.isEmpty(token)) return null
    val claimsJws: Jws<Claims> =
        Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token)
    val claims = claimsJws.body
    return claims["password"] as String?
}