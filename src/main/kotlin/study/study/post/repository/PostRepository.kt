package study.study.post.repository

import org.springframework.data.jpa.repository.JpaRepository
import study.study.post.entity.Post

//interface : 객체지향언어에서

interface PostRepository : JpaRepository<Post, Long>