package study.study.member.entity

import jakarta.persistence.*
//import study.study.common.status.Gender
import study.study.common.status.DormType
import study.study.common.status.ROLE
import study.study.member.dto.MemberDtoResponse

//import java.time.LocalDate


@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_member_login_id", columnNames = ["loginId"])]
)
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    val loginId: String,

    @Column(nullable = false, length = 100)
    val password: String,

    @Column(nullable = false, length = 10)
    val name: String,
/**
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val birthDate: LocalDate,
*/
    @Column(nullable = false, length = 4)
    @Enumerated(EnumType.STRING)
    val dormType: DormType,

    @Column(nullable = false, length = 30)
    val email: String,


){
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    val memberRole: List<MemberRole>? = null

    fun toDto(): MemberDtoResponse =
        MemberDtoResponse(id!!, loginId, name, dormType.desc, email)
}

@Entity
class MemberRole(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    val role: ROLE,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_member_role_member_id"))
    val member: Member,
    ){
}
//스터디용 코드


