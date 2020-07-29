package com.example.nurbk.ps.news.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nurbk.ps.news.R
import com.example.nurbk.ps.news.model.Article
import kotlinx.android.synthetic.main.news_layout.view.*

class NewsAdapter(
) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun getNewsAt(position: Int): Article {
        return differ.currentList[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = getNewsAt(position)
        holder.itemView.apply {
            if (article.title!!.length > 60) {
                article.title = article.title!!.substring(0, 60)
            }

            tvTitle.text = article.title
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }

    }




    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}