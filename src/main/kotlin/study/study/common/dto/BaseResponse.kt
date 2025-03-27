package study.study.common.dto

import study.study.common.status.ResultCode


data class BaseResponse <T> (
    val resultCode: String = ResultCode.SUCCESS.name, //결과
    val data: T? = null, //조회나 처리시 결과 반환할 값을 담기 위해 T?
    val message: String = ResultCode.SUCCESS.msg, //결과에 해당하는 메세지
)