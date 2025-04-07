package study.study.common.status

enum class DormType(val desc: String) {
    GOA( "고운A"),
    GOB( "고운B"),
    GOC( "고운C"),
    GS11( "경상11"),
    GS12( "경상12"),
    GS13( "경상13"),
    GS14( "경상14"),
}

enum class ResultCode(val msg: String) {
    SUCCESS ("정상 처리 되었습니다."),
    ERROR( " 에러가 발생했습니다.")

}

//..