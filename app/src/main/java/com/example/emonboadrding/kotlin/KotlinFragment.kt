package com.example.emonboadrding.kotlin

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.emonboadrding.R
import com.example.emonboadrding.kotlin.task2.ScreenStartAnalytics
import com.example.emonboadrding.kotlin.task2.ScreenStartAnalyticsImpl


class KotlinFragment : Fragment(R.layout.fragment_kotlin),
    ScreenStartAnalytics by ScreenStartAnalyticsImpl {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerLifecycleOwner(this)
    }

}