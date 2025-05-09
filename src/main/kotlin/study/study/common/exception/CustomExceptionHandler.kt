package study.study.common.exception

import org.apache.coyote.BadRequestException
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import study.study.common.dto.BaseResponse
import study.study.common.status.ResultCode
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException


@RestControllerAdvice
class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun methodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Map<String, String>>>{
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception Message"


        }


        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(InvalidinputException::class)
    protected fun invalidInputException(ex: InvalidinputException):ResponseEntity<BaseResponse<Map<String, String>>>{
        val errors = mapOf(ex.fieldName to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
        // 올바르지 못한 입력 예외 에러
    }

    @ExceptionHandler(Exception::class)
    protected fun defaultException(ex: Exception):ResponseEntity<BaseResponse<Map<String, String>>>{
        val errors = mapOf("미처리 에러" to (ex.message ?: "Not Exception Message"))
        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
        //그 외에서 발생한 에러
    }

    @ExceptionHandler(BadCredentialsException::class)
    protected fun badCredentialsException(ex: BadCredentialsException):ResponseEntity<BaseResponse<Map<String, String>>>{
        val errors = mapOf("로그인 실패" to "아이디 혹은 비밀번호를 다시 확인하세요.")
        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
        //그 외에서 발생한 에러
    }

}