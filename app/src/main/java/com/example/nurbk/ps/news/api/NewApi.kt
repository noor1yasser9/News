package com.example.nurbk.ps.news.api

import com.example.nurbk.ps.news.model.NewsResponse
import com.example.nurbk.ps.news.unit.Constants
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApi {

    @GET("v2/top-headlines")
    fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
//        @Query("page")
//        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Single<NewsResponse>


    @GET("v2/top-headlines?language=en&q=covid-19&sortBy=popularity")
    fun getNewsCv(@Query("apiKey") apiKey: String = Constants.API_KEY): Single<NewsResponse>


    @GET("v2/everything")
     fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Single<NewsResponse>



    @GET("v2/top-headlines")
    fun getBreakingNewsCategory(
        @Query("country")
        countryCode: String = "us",
        @Query("category")
        category: String = "business",
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Single<NewsResponse>

}