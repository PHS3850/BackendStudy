package study.study.member.service

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import study.study.common.authority.JwtTokenProvider
import study.study.common.authority.TokenInfo
import study.study.common.exception.InvalidinputException
import study.study.common.status.ROLE
import study.study.member.dto.LoginDto
import study.study.member.dto.MemberDtoRequest
import study.study.member.dto.MemberDtoResponse
import study.study.member.entity.Member
import study.study.member.entity.MemberRole
import study.study.member.repository.MemberRepository
import study.study.member.repository.MemberRoleRepository


@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    /**
      회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest): String{
    //id 중복 검사
        println("signUp")
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if(member != null){
            throw InvalidinputException("loginId","이미 등록된 ID 입니다")
        }
        println("member check")

        member = memberDtoRequest.toEntity()
        memberRepository.save(member)
        println("***************1")

        val memberRole = MemberRole(null, ROLE.MEMBER, member )
        memberRoleRepository.save(memberRole)
        println("***************2")

        //>?

        return "회원가입이 완료되었습니다."

    }

    fun login(loginDto: LoginDto): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, loginDto.password)
        val authorities = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authenticationToken)


    }

    /**
     * 내 정보 조회
     */
    fun  searchMyInfo(id: Long): MemberDtoResponse {
        val member :Member = memberRepository.findByIdOrNull(id) ?: throw InvalidinputException("id","회원번호(${id})가 존재하지 않는 유저입니다.")
        return member.toDto()
    }


    /**
     * 내정보 수정
     */

    fun saveMyInfo(memberDtoRequest: MemberDtoRequest): String {
        val member: Member = memberDtoRequest.toEntity()
        memberRepository.save(member)
        return "수정 완료되었습니다."

    }
}