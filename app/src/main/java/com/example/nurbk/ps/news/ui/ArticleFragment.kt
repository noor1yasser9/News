package com.example.nurbk.ps.news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import com.example.nurbk.ps.news.NewsActivity
import com.example.nurbk.ps.news.R
import com.example.nurbk.ps.news.adapter.DoppelgangerAdapter
import com.example.nurbk.ps.news.model.Article
import com.example.nurbk.ps.news.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_aricle.*
import kotlinx.android.synthetic.main.fragment_news.*

class ArticleFragment : Fragment(R.layout.fragment_aricle) {

    lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as NewsActivity).viewModel

        val article = requireArguments().getSerializable("article") as Article


        webView.apply {
            webChromeClient = WebChromeClient()
            loadUrl(article.url)
        }


        fab.setOnClickListener {
            viewModel.saveNews(article)
        }



    }
}