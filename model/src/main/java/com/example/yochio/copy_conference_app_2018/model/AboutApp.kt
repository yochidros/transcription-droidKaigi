package com.example.yochio.copy_conference_app_2018.model

/**
 * Created by yotio on 2018/02/06.
 */
sealed class AboutApp(
        open val id: Int,
        open val name: Int,
        open val description: Int,
        open val navigationUrl: String?
) {

    data class Item(
            override val id: Int,
            override val name: Int,
            override val description: Int,
            override val navigationUrl: String?
    ) : AboutApp(id, name, description, navigationUrl)

    data class HeadItem(
            override val id: Int,
            override val name: Int,
            override val description: Int,
            override val navigationUrl: String?,
            val faceBookUrl: String,
            val twitterUrl: String,
            val githubUrl: String,
            val mediumUrl: String
    ) : AboutApp(id, name, description, navigationUrl)
}