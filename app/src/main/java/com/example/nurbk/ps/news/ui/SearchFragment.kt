package com.example.nurbk.ps.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nurbk.ps.news.NewsActivity
import com.example.nurbk.ps.news.R
import com.example.nurbk.ps.news.adapter.AutoCompleteNewsAdapter
import com.example.nurbk.ps.news.adapter.NewsAdapter
import com.example.nurbk.ps.news.adapter.NewsAdapters
import com.example.nurbk.ps.news.model.Article
import com.example.nurbk.ps.news.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.breaking_news_fragment.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapters
    private lateinit var adapter: AutoCompleteNewsAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as NewsActivity).viewModel

        newsAdapter = NewsAdapters()
        rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }


//        var job: Job? = null
//        etSearch.addTextChangedListener { editable ->
////            job?.cancel()
////            job = MainScope().launch {
////                delay(500L)
////                editable?.let {
////                    if (editable.toString().isNotEmpty()) {
////                        viewModel.getSearchNews(editable.toString())
////                    }
////                }
////            }
////        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {
            adapter = AutoCompleteNewsAdapter(requireContext(), it.articles)
            actv.setAdapter(adapter)
            adapter.setOnItemClickListener {
                Log.e("tttttt","${it.description} ${it.title}")
            }
        })

        viewModel.searchNews.observe(requireActivity(), Observer {
            newsAdapter.differ.submitList(it.articles.toList())
            Log.e("tttOB", it.articles[0].title)

        })

        newsAdapter.setOnItemClickListener {
            sendDataArticleFragment(it)
        }

    }

    private fun sendDataArticleFragment(article: Article) {
        val bundle = Bundle().apply {
            putSerializable("article", article)
        }
        findNavController().navigate(
            R.id.action_searchFragment_to_articleFragment,
            bundle
        )
    }
}