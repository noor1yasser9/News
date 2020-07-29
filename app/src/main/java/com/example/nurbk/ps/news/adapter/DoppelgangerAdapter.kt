package com.example.nurbk.ps.news.adapter


import android.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter


class DoppelgangerAdapter(activity: AppCompatActivity,
                          val itemsCount: Int) : FragmentStateAdapter(activity) {

    var lf = mutableListOf<Fragment>()
    var lt = mutableListOf<String>()

    override fun getItemCount(): Int {
        return lt.size
    }

    override fun createFragment(position: Int): Fragment {
        return lf[position]
    }


    // Function For Add Fragments in ViewPager
    fun addFragment(f:Fragment,t:String){
        lf.add(f)
        lt.add(t)
    }



}