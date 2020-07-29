package com.example.nurbk.ps.news.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nurbk.ps.news.db.ArticleDatabase
import com.example.nurbk.ps.news.model.Article
import com.example.nurbk.ps.news.model.NewsResponse
import com.example.nurbk.ps.news.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import io.reactivex.Completable
import io.reactivex.disposables.Disposable

class NewsViewModel(
    application: Application

) : AndroidViewModel(application) {

    private val TAG = "News View Model"


    private val compositeDisposable = CompositeDisposable()


    private val newsResponse = NewsRepository(application, ArticleDatabase.invoke(application))

    val breakingNews = MutableLiveData<NewsResponse>()
    val breakingNewsCv = MutableLiveData<NewsResponse>()
    val searchNews = MutableLiveData<NewsResponse>()
    val saveNews = MutableLiveData<List<Article>>()


    private fun getBreakingNews(countryCode: String, pageNumber: Int) {

        val observable = newsResponse.getBreakingNews(countryCode, pageNumber)

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                breakingNews.value = it
//                Completable.fromAction {
//                newsResponse.insetList(it.articles)
//                }.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe()
//                    .also {di->
//                        compositeDisposable.add(di)
//                    }
            }, {
                Log.e(TAG, "getBreakingNews On Error: ${it.message}")
            })

        compositeDisposable.add(observable)
    }

    init {
        getBreakingNews("us", 1)
        getBreakingCv()
        getAllArticle()
    }


    private fun getBreakingCv() {

        val observable = newsResponse.getBreakingNewsCv()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                breakingNewsCv.value = it
                Log.e("ttt", "${it.articles[0].title}")
            }, {
                Log.e(TAG, "getBreakingNews On Error: ${it.message}")
            })

        compositeDisposable.add(observable)
    }


    fun getSearchNews(searchQuery: String) {

        val observable = newsResponse.getSearchNewsCv(searchQuery, 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                searchNews.value = it
                Log.e("ttt", "${it.articles[0].title}")
            }, {
                Log.e(TAG, "getSearchNews On Error: ${it.message}")
            })

        compositeDisposable.add(observable)
    }


    private fun getAllArticle() =
        newsResponse.getAllArticle().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                saveNews.value = it
            }, {
                Log.e(TAG, "getAllArticle On Error: ${it.message}")
            }).also {
                compositeDisposable.add(it)
            }


    fun saveNews(article: Article): Disposable =
        Completable.fromAction {
            newsResponse.insert(article)
            getAllArticle()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .also {
                compositeDisposable.add(it)
            }


    fun deleteNew(article: Article): Disposable =
        Completable.fromAction {
            newsResponse.deleteArticle(article)
            getAllArticle()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .also {
                compositeDisposable.add(it)
            }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()

    }


    fun getBreakingNewsCategory(nameCategory: String) {

        val observable = newsResponse
            .getBNCategory(nameCategory)

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                breakingNews.value = it
            }, {
                Log.e(TAG, "getBreakingNews On Error: ${it.message}")
            })

        compositeDisposable.add(observable)
    }

}