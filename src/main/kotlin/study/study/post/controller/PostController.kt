package study.study.post.controller

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import study.study.common.dto.BaseResponse
import study.study.post.dto.PostDtoRequest
import study.study.post.entity.Post
import study.study.post.service.PostService


@RestController
class PostController (
    private val postService: PostService
){
    /**
     * 게시글 작성?
     */
    @PostMapping("/posting")
    fun post(@RequestBody @Valid postDtoRequest: PostDtoRequest): BaseResponse<String> {
        val resultMsg : String = postService.post(postDtoRequest)
        return BaseResponse(resultMsg)

    }
}


/*
class MemberController (
    private val memberService: MemberService
) {
    /**
     * 회원가입
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest) : BaseResponse<Unit> {
        val resultMsg: String = memberService.signUp(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }

 */