package com.example.nurbk.ps.news.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nurbk.ps.news.NewsActivity
import com.example.nurbk.ps.news.R
import com.example.nurbk.ps.news.adapter.NewsAdapters
import com.example.nurbk.ps.news.model.Article
import com.example.nurbk.ps.news.viewModel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_save_news.*
import kotlinx.android.synthetic.main.fragment_search.*

class SaveNewsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_save_news, container, false)
    }

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapters
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity() as NewsActivity).viewModel

        newsAdapter = NewsAdapters()
        rvSaveNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)

        }

        viewModel.saveNews.observe(requireActivity(), Observer {
            newsAdapter.differ.submitList(it)
        })

        newsAdapter.setOnItemClickListener {
            sendDataArticleFragment(it)
        }


        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val article = newsAdapter.differ.currentList[viewHolder.adapterPosition]

                viewModel.deleteNew(article).also {
                    Toast.makeText(requireContext(), "Deleted successfully", Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSaveNews)
        }

    }

    private fun sendDataArticleFragment(article: Article) {
        Bundle().apply {
            putSerializable("article", article)
        }.also {
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                it
            )
        }
    }
}