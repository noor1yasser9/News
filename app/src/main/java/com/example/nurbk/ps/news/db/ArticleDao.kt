package com.example.nurbk.ps.news.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nurbk.ps.news.model.Article
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Query("SELECT * FROM article")
    fun getArticle(): Single<List<Article>>

    @Delete
    fun delete(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(article: MutableList<Article>)

}