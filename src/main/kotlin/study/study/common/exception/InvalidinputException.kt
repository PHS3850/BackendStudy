package study.study.common.exception

class InvalidinputException (
    val fieldName: String = "",
    message: String = "Invalid input"
) : RuntimeException(message)