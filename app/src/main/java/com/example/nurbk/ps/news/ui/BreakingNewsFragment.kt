package com.example.nurbk.ps.news.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nurbk.ps.news.NewsActivity
import com.example.nurbk.ps.news.R
import com.example.nurbk.ps.news.adapter.NewsAdapter
import com.example.nurbk.ps.news.model.Article
import com.example.nurbk.ps.news.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.breaking_news_fragment.*

class BreakingNewsFragment : Fragment(R.layout.breaking_news_fragment)
//    , NewsAdapter.OnClickItemNewsListener
{

    lateinit var viewModel: NewsViewModel
    private val adapter by lazy {
        NewsAdapter()
    }
    private val adapterCv by lazy {
        NewsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (requireActivity() as NewsActivity).viewModel

        rvNewsData.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rvNewsData.adapter = adapter
        rvNewsData.setHasFixedSize(true)

        viewModel.breakingNews.observe(requireActivity(), Observer {
            adapter.differ.submitList(it.articles)

        })

        rvNewsDataCv.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        rvNewsDataCv.adapter = adapterCv
        rvNewsDataCv.setHasFixedSize(true)
        viewModel.breakingNewsCv.observe(requireActivity(), Observer {
            adapterCv.differ.submitList(it.articles)
            Log.e("tttMain", "${it.articles[0].title}")
        })

        adapter.setOnItemClickListener {
            sendDataArticleFragment(it)
        }
        adapterCv.setOnItemClickListener {
            sendDataArticleFragment(it)
        }

        btnSearch.setOnClickListener {
            findNavController().navigate(
                R.id.action_breakingNewsFragment2_to_searchFragment
            )
        }

        home_call_button.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:119")
            requireContext().startActivity(intent)
        }


    }


    private fun sendDataArticleFragment(article: Article) {
        val bundle = Bundle().apply {
            putSerializable("article", article)
        }
        findNavController().navigate(
            R.id.action_breakingNewsFragment2_to_articleFragment,
            bundle
        )
    }


}