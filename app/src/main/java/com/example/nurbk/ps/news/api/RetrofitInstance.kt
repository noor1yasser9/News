package com.example.nurbk.ps.news.api

import com.example.nurbk.ps.news.model.NewsResponse
import com.example.nurbk.ps.news.unit.Constants
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {


    companion object {
        private var instance: RetrofitInstance? = null
         var api: NewApi? = null

        init {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            api = retrofit.create(NewApi::class.java)
        }

        private fun getInstance(): RetrofitInstance {
            if (instance == null) {
                instance = getInstance()
            }
            return instance!!
        }



    }


}