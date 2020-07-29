package com.example.nurbk.ps.news.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import com.example.nurbk.ps.news.R
import com.example.nurbk.ps.news.model.Article
import kotlinx.android.synthetic.main.article_autocomplete_row.view.*

class AutoCompleteNewsAdapter(context: Context, article: MutableList<Article>) :
    ArrayAdapter<Article>(
        context,
        0,
        article
    ) {

    var articleList = ArrayList<Article>(article)


    val articleFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val result = FilterResults()
            val suggestions = ArrayList<Article>()

            if (constraint == null || constraint.isEmpty()) {
                suggestions.addAll(articleList)

            } else {
                val filterPattern = constraint.toString().toLowerCase().trim()

                for (article in articleList) {
                    if (article.title!!.toLowerCase().contains(filterPattern)) {
                        suggestions.add(article)
                    }
                }

            }
            result.values = suggestions
            result.count = suggestions.size

            return result

        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            clear()
            addAll(results!!.values as MutableList<Article>)

            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as Article).title!!
        }
    }

    override fun getFilter(): Filter {
        return articleFilter
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val root = LayoutInflater.from(context)
            .inflate(R.layout.article_autocomplete_row, parent, false)
        val article = getItem(position)
        root.text_view_name.text = article!!.title
        root.text_view_desc.text = article.description

        root.setOnClickListener {
            onItemClickListener?.let { it(article) }
        }
        return root
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}
