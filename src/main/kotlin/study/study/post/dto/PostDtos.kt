package study.study.post.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import study.study.post.entity.Post
import study.study.post.entity.PostList
import java.time.LocalDateTime

data class PostDtoRequest(

    val id : Long? = null,

    @field:NotBlank
    @JsonProperty("title")
    private val _title: String?,

    @JsonProperty("text")
    @field:NotBlank
    private val _text: String?,


    private val createDate: LocalDateTime = LocalDateTime.now()


){
    val title: String
        get() = _title!!.toString()
    val text: String
        get() = _text!!.toString()

    fun toEntity(writer: String) : Post {
        return Post(
            null, title, text, writer, createDate
        )
    }
}

//리스트 받고 내용 받는걸 하나 더 만들어야하나?