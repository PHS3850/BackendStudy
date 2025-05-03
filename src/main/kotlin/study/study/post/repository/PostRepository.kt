package study.study.post.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.post.entity.Post


interface PostRepository : JpaRepository<Post, Long>{
    fun findPostById(postId: Long): Post?

}
