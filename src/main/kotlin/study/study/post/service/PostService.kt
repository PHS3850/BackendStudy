package study.study.post.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

import study.study.post.dto.PostDtoRequest
import study.study.post.repository.PostRepository

@Transactional
@Service
class PostService (
    private val postRepository: PostRepository,
) {
    /**
     * 게시글 작성
     */
    fun post(postDtoRequest: PostDtoRequest): String {
        val post = postDtoRequest.toEntity()
        postRepository.save(post)
        return "게시글 작성 완료"
    }

}
/*
무슨 기능을 만들까...
*/