package study.study.post.service

import jakarta.transaction.Transactional
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import study.study.common.exception.InvalidinputException
import study.study.member.repository.MemberRepository

import study.study.post.dto.PostDtoRequest
import study.study.post.entity.Post
import study.study.post.repository.PostRepository

@Transactional
@Service
class PostService (
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository,
) {
    /**
     * 게시글 작성
     */
    fun post(
        postDtoRequest: PostDtoRequest,
        userId: Long,
    ): String {
        val member =
            memberRepository.findByIdOrNull(userId) ?: throw InvalidinputException("id", "회원번호(${id})가 존재하지 않는 유저입니다.")

        val post = postDtoRequest.toEntity(member.name)
        postRepository.save(post)
        return "게시글을 작성했습니다."
    }

    fun showPostList(): MutableList<Post> {
        return postRepository.findAll()
    }
 // 외않덴대
    fun getPost(postId: Long) : Post {
        val post = postRepository.findPostById(postId)
            ?: throw InvalidinputException("게시글 번호 : $postId 존재하지 않는 게시글 입니다.")
        return post
    }
}