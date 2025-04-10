package study.study.post.entity

import jakarta.persistence.*


@Entity
class Post(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false, length = 25)
    val title: String,


    @Column(nullable = false, length = 500)
    val text: String,
){

}


//클래스를 만든 이유?