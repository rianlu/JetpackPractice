package com.wzl.viewpager2demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = 3

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> FirstFragment()
                    1 -> SecondFragment()
                    else -> ThirdFragment()
                }
            }
        }

        TabLayoutMediator(
            tabLayout,
            viewPager2,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    when (position) {
                        0 -> tab.text = "First"
                        1 -> tab.text = "Second"
                        else -> tab.text = "Third"
                    }
                }
            }).attach()  //一定要写attach()！！！


        // 简写
        /*TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "First"
                1 -> tab.text = "Second"
                else -> tab.text = "Third"
            }
        }.attach()*/
    }
}
