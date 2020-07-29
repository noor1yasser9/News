package com.example.nurbk.ps.news.repository

import android.app.Application
import com.example.nurbk.ps.news.api.RetrofitInstance
import com.example.nurbk.ps.news.db.ArticleDatabase
import com.example.nurbk.ps.news.model.Article
import com.example.nurbk.ps.news.unit.Constants
import retrofit2.http.Query

class NewsRepository(
    val application: Application,
    val db: ArticleDatabase
) {


    fun getBreakingNews(countyCode: String, pageNumber: Int) =
        RetrofitInstance.api!!.getBreakingNews(
            countyCode
//            , pageNumber
        )

    fun getBreakingNewsCv() =
        RetrofitInstance.api!!.getNewsCv()

    fun getSearchNewsCv(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api!!.searchForNews(searchQuery, pageNumber)


    fun insert(article: Article) = db.articleDao().insert(article)

    fun deleteArticle(article: Article) = db.articleDao().delete(article)

    fun getAllArticle() = db.articleDao().getArticle()


    fun insetList(article: MutableList<Article>) = db.articleDao().insertList(article)


    fun getBNCategory(category: String) = RetrofitInstance
        .api!!.getBreakingNewsCategory("us", category)
}