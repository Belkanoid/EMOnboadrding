package com.example.emonboadrding.kotlin.task2

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


interface ScreenStartAnalytics {

    fun registerLifecycleOwner(owner: LifecycleOwner)
}

object ScreenStartAnalyticsImpl : ScreenStartAnalytics, DefaultLifecycleObserver {

    private var screenStartTimestamp: Long = 0L
    private val thread = Thread {
            while (true) {
                if(Thread.currentThread().isInterrupted) break
                Log.d(this.javaClass.name, screenStartTimestamp.toString())
                Thread.sleep(3000)
            }

        }

    override fun registerLifecycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }


    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        screenStartTimestamp = System.currentTimeMillis()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        thread.start()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        thread.interrupt()
    }

}
