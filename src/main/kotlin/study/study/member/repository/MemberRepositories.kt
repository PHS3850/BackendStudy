package study.study.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.common.status.DormType
import study.study.member.entity.Member
import study.study.member.entity.MemberRole

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String) : Member?
    fun findAllByDormType(dormType: DormType) : MutableList<Member>
}

/**
 * 토큰을 까서 기숙사 타입이 같은 사람만 보여주기?
 */

interface MemberRoleRepository: JpaRepository<MemberRole, Long>