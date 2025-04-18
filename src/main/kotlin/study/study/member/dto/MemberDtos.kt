package study.study.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import study.study.common.annotation.ValidEnum
import study.study.common.status.Gender
import study.study.member.entity.Member
import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class MemberDtoRequest(
    val id: Long?,


    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId: String?,


    @field:NotBlank
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = "영문, 숫자, 특수문자를 포함한 8~20자리로 입력해주세요."
    )
    @JsonProperty("password")
    private val _password: String?,


    @field:NotBlank
    @JsonProperty("name")
    private val _name: String?,


    @field:NotBlank
    @field:Pattern(
        regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = "날짜형식(YYYY-MM-DD)을 확인해주세요"
    )
    @JsonProperty("birthDate")
    private val _birthDate: String?,


    @field:NotBlank
    @field:ValidEnum(enumClass = Gender::class, message = "MAN 이나 WOMAN 중 하나를 선택해주세요.")
    @JsonProperty("gender")
    private val _gender: String?,

    @field:NotBlank
    @field:Email
    @JsonProperty("email")
    private val _email: String?,

    // 에러코드 출력에서 이메일은 딱히 뭘 추가 안하나? 영상2.3 14.11 부근...
    // 공백일 수 있습니다 < 이건 어디서 나온거지??
    // 테스트 해보는법을 물어봐야겟다... 3.26 작성 완료

) {
    val loginId: String
        get() = _loginId!!
    val password: String
        get() = _password!!
    val name: String
        get() = _name!!
    val birthDate: LocalDate
        get() = _birthDate!!.toLocalDate()
    val gender: Gender
        get() = Gender.valueOf(_gender!!)
    val email: String
        get() = _email!!
    private fun String.toLocalDate(): LocalDate =
        LocalDate.parse(this, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    fun toEntity() : Member = //멤버 생성자를 사용해 엔티티 반환
        Member(id, loginId, password, name, birthDate, gender, email)
}
