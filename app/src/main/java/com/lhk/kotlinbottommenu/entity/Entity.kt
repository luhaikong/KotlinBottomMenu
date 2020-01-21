package com.lhk.kotlinbottommenu.entity

class Entity {
    data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T?)

    data class User(
        val collectIds: List<Int>, val email: String,
        val icon: String, val id: Int,
        val password: String, val type: Int, val username: String?
    )


    data class TypeTree<out P>(
        val children: List<P>?, val courseId: Int, val id: Int,
        val name: String, val order: Int, val parentChapterId: Int,
        val visible: Int
    )


    data class TypeTreeContent(
        val apkLink: String,
        val audit: Int,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val niceShareDate: String,
        val origin: String,
        val prefix: String,
        val projectLink: String,
        val publishTime: Long,
        val selfVisible: Int,
        val shareDate: Long,
        val shareUser: String,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Tag>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
    )

    data class Tag(
        val name: String,
        val url: String
    )
}