package study.study.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.member.entity.Member
import study.study.member.entity.MemberRole

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?
    fun findALlByDormType(dormType: String): List<Member>
}

/**
 * 토큰을 까서 기숙사 타입이 같은 사람만 보여주기?
 */

interface MemberRoleRepository: JpaRepository<MemberRole, Long>