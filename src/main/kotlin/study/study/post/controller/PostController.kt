package study.study.post.controller

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import study.study.common.dto.BaseResponse
import study.study.common.dto.CustomUser
import study.study.post.dto.PostDtoRequest
import study.study.post.entity.Post
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
    fun posting(@RequestBody @Valid postDtoRequest: PostDtoRequest): BaseResponse<String> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val resultMsg = postService.posting(postDtoRequest, userId)
        return BaseResponse(resultMsg)
    }
    /**
     * 뭐가문제인데
     */

    /**
     * 전체 게시글 가져오기
     */
    @GetMapping("/")
    fun getAllPosts() : BaseResponse<MutableList<Post>> {
        val list = postService.getAllPosts()
        return BaseResponse(data = list)
    }
    /**
     * 특정 게시판 가져오기
     */
    @GetMapping("/{id}")
    fun getPost(@PathVariable id : Long) : BaseResponse<Post> {
        val result = postService.getPost(id)
        return BaseResponse(data = result)
    }
}
