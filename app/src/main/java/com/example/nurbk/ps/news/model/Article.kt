package com.example.nurbk.ps.news.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nurbk.ps.news.model.Source
import java.io.Serializable


@Entity(tableName = "article")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    var title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable