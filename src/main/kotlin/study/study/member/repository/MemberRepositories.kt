package study.study.member.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.member.entity.Member
import study.study.member.entity.MemberRole

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?
    //id중복검사?

}

interface MemberRoleRepository: JpaRepository<MemberRole, Long>