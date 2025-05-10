package study.study.post.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.post.entity.Post


interface PostRepository : JpaRepository<Post, Long>{
    fun findPostById(postId: Long): Post?
//   fun findPostAtTitleWriter(title: String, writer: String): Post?
}
//게시글을 가져올 때 제목이랑 작성자, 작성날짜만 보이게 하고싶으면
//findAll이 아니라 특정 항목만 해서 가져오면 될거같다
