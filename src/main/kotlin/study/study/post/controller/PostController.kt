package study.study.post.controller

import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import study.study.common.dto.BaseResponse
import study.study.common.dto.CustomUser
import study.study.post.dto.PostDtoRequest
import study.study.post.entity.Post
import study.study.post.service.PostService
import study.study.post.service.

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
        //println("post 함수 설정")
        val resultMsg = postService.post(postDtoRequest, userId)
        //println("포스트서비스로?")
        return BaseResponse(resultMsg)

    }
    @GetMapping("/")
    fun getPostList() : BaseResponse<MutableList<Post>> {

        val list = PostService.showPostList()
        return BaseResponse(data = list)
    }


    @GetMapping("/{id}")
    fun getPosts(@PathVariable id : Long) : BaseResponse<Post> {
        val result = postService.getPost(id)
        return BaseResponse(data = result)
    }

}

/**
 * 리스트? 전체 리스트 꺼내고 > 특정 리스트 꺼내기?
 */