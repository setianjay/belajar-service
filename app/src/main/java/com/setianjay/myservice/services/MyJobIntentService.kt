package com.setianjay.myservice.services


import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyJobIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork running")
        val duration = intent.getLongExtra(EXTRA_DURATION, 0)
        try {
            Thread.sleep(duration)
            Log.d(TAG, "onHandleWork stopped")
        }catch (e: InterruptedException){
            e.printStackTrace()
            Thread.currentThread().interrupt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy running")
    }

    companion object{
        private val TAG = MyJobIntentService::class.java.simpleName
        private const val JOB_ID = 1000
        const val EXTRA_DURATION = "extra_duration"

        fun enqueueWork(context: Context, intent: Intent){
            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, intent)
        }
    }
}