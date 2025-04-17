package study.study.post.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class Post(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false, length = 25)
    val title: String,


    @Column(nullable = false, length = 500)
    val text: String,


    @Column(nullable = false, length = 10)
    val writer: String,

    @Column(nullable = false, length = 15)
    val createDate: LocalDateTime,

    //작성 날짜 추가?


){

}
// 4.10 작성자 항목 추가