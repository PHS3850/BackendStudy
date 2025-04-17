package study.study.post.controller

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import study.study.common.dto.BaseResponse
import study.study.common.dto.CustomUser
import study.study.post.dto.PostDtoRequest
import study.study.post.service.PostService

@RequestMapping("api/post")
@RestController
class PostController (
    private val postService: PostService
){
    /**
     * 게시글 작성 - 토큰 검증
     */
    @PostMapping("/")
    fun post(@RequestBody @Valid postDtoRequest: PostDtoRequest): BaseResponse<String> {
        val userId = (SecurityContextHolder
            .getContext()
            .authentication
            .principal as CustomUser)
            .userId
        println("post 함수 설정")
        val resultMsg : String = postService.post(postDtoRequest, userId)
        println("포스트서비스로?")
        return BaseResponse(resultMsg)

    }

}
