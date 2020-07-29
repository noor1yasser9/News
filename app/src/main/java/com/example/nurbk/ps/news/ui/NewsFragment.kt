package com.example.nurbk.ps.news.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.nurbk.ps.news.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.*

class NewsFragment : Fragment(R.layout.fragment_news) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tablayout_headlines.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
              
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

            }
        })
    }

}