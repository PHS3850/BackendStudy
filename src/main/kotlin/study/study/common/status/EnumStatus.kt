package study.study.common.status

enum class Gender(val desc: String) { //desc는 뭐하는 클래스?
    MAN( "남"),
    WOMAN( "여")
}

enum class ResultCode(val msg: String) {
    SUCCESS ("정상 처리 되었습니다."),
    ERROR( " 에러가 발생했습니다.")

}