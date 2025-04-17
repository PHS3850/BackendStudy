package study.study.post.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import study.study.post.entity.Post
import java.io.Writer
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
 *
 * 추가하고 싶은 기능 - 작성글 조회
 *
 * 여기서 val titleId를 0000부터 9999까지 자동으로 부여하고  <- 9999번쨰 글 작성시 00000으로 새로 순서를 만둘어야하나? 아니면 다른 서버를 만들어야하나?
 *
 *
 * post/id?=0001  이런식으로 엔드포인트를 지정하고 컨트롤러에 NNNN번째 글 요청하면 그 글 제목과 내용을 볼 수 있는 기능?
 *
 */

/*
int add(int a, int b){
    return a+b;
}

int main(){
    add(2,3);
}


 Post(null,title,text,writer,createDate)
 */