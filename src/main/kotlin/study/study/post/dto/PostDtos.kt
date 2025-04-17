package study.study.post.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import study.study.post.entity.Post
import java.time.LocalDateTime

data class PostDtoRequest(

    val id : Long?,

    @field:NotBlank
    @JsonProperty("title")
    private val _title: String?,

    @JsonProperty("text")
    @field:NotBlank
    private val _text: String?,


    private val createDate: LocalDateTime = LocalDateTime.now()


    // 작성자 추가 완료 4.10
){
    val title: String
        get() = _title!!
    val text: String
        get() = _text!!

    fun toEntity(writer: String) : Post = Post(
        null,title,text,writer,createDate
    )
}



/**
 * int add(int a, int b){
 *     return a+b;
 * }
 *
 * int main(){
 *     add(2,3);
 * }
 *
 *
 *  Post(null,title,text,writer,createDate)
 *
 */