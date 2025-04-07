package study.study.member.entity

import jakarta.persistence.*
//import study.study.common.status.Gender
import study.study.common.status.DormType
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
/*
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    val birthDate: LocalDate,
*/
    @Column(nullable = false, length = 4)
    @Enumerated(EnumType.STRING)
    val dormType: DormType,

    @Column(nullable = false, length = 30)
    val email: String,



    )

// {} 와 () 사용 - 매개변수와 함수 내 변수

// 공부해가자


//..