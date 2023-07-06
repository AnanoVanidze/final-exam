package com.example.android_final.ui

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.android_final.R
import com.example.android_final.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PagerAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewPager()
    }



    private fun setViewPager() {
        val ridesPages = arrayOf(
            "CURRENCY",
            "CONVERTER"
        )
        val viewPager = binding.viewPager
        adapter = PagerAdapter(childFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = ridesPages[position]
        }.attach()

        val root: View = binding.tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            (root).showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(ContextCompat.getColor(requireContext(), R.color.main_color))
            drawable.setSize(2, 1)
            (root).dividerPadding = 15
            (root).dividerDrawable = drawable
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}