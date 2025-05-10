package study.study.post.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.IntLikeSerializer
import jakarta.validation.constraints.NotBlank
import study.study.post.entity.Post
import java.time.LocalDateTime

data class PostDtoRequest(
    val id : Long? = null,

    @field:NotBlank
    @JsonProperty("title")
    private val _title: String?,

    @JsonProperty("content")
    @field:NotBlank
    private val _content: String?,

    private val likes : Long = 0,
    private val createDate: LocalDateTime = LocalDateTime.now()
){
    val title: String
        get() = _title!!.toString()
    val content: String
        get() = _content!!.toString()

    fun toEntity(writer: String) : Post {
        return Post(
            null, title, content, writer, likes, createDate)
    }
}