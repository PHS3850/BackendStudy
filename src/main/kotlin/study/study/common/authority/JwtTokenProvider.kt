package study.study.common.authority

import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import study.study.common.dto.CustomUser
import java.util.*


const val EXPIRATION_MILLISECONDS: Long = 1000 * 60 * 30 //30분

@Component
class JwtTokenProvider {

    @Value("\${jwt.secret}")
    lateinit var secretKey: String


    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)) }

    /**
     * 토큰 생성
     */
    fun createToken(authentication: Authentication): TokenInfo {
        val authority: String = authentication
            .authorities
            .joinToString(separator = ",", transform = GrantedAuthority::getAuthority)

        val now = Date()
        val accessExpiration = Date(now.time + EXPIRATION_MILLISECONDS)


        //Access Token
        val accessToken = Jwts
            .builder()
            .subject(authentication.name)
            .claim("auth", authentication)
            .claim("userId",authentication.principal as CustomUser)
            .issuedAt(now)
            .expiration(accessExpiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()

        return TokenInfo("Bearer",accessToken)
    }

    /**
     *  토큰 정보 추출
     */

    fun getAuthentication(token: String): Authentication {

        val claims : Claims = getClaims(token)

        val auth = claims["auth"] ?: throw RuntimeException("잘못된 토큰입니다.")
        val userId = claims["userId"] ?: throw RuntimeException("잘못된 토큰 입니다.")

        //권한 정보 추출
        val authorities: Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map{SimpleGrantedAuthority(it)}

        val principal: UserDetails = CustomUser(userId.toString().toLong(),claims.subject,"",authorities)

        return UsernamePasswordAuthenticationToken(principal,"",authorities)


    }

    /**
     * Token 검증?
     */
    fun validateToken(token: String): Boolean {
        try {
            getClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {

                is SecurityException -> {}          // Invalid JWT Token
                is MalformedJwtException -> {}      // Invalid JWT Token
                is ExpiredJwtException -> {}        // Expired JWT Token
                is UnsupportedJwtException -> {}    // Unsupported JWT Token
                is IllegalArgumentException -> {}   // JWT claims string as empty

                else -> {}                          // else

            }

            println(e.message)

        }

        return false
    }
//이걸 써야하는데...


    private fun getClaims(token: String): Claims =
        Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload





}
