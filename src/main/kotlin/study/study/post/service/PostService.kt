package study.study.post.service

import jakarta.transaction.Transactional
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import study.study.common.exception.InvalidinputException
import study.study.member.repository.MemberRepository

import study.study.post.dto.PostDtoRequest
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


        val member = memberRepository.findByIdOrNull(userId) ?: throw InvalidinputException("id", "회원번호(${id})가 존재하지 않는 유저입니다.")
        println("멤버 체크, null이면 throw 문장")
        member.name
        println("멤버.이름 넣는건가?")
        val post = postDtoRequest.toEntity(member.name)
        println("포스트dto리퀘스트에 담아서 투엔티티로")
        postRepository.save(post)
        println("레포지토리에 저장?")
        return "게시글을 작성했습니다."
    }
}