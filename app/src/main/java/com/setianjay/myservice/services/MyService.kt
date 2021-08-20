package com.setianjay.myservice.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class MyService : Service() {
    private val TAG = MyService::class.java.simpleName
    private val job = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + job)

    override fun onBind(intent: Intent): IBinder {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG,"Service running")
        serviceScope.launch {
            delay(3000)
            stopSelf()
            Log.d(TAG, "Service stopped")
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        Log.d(TAG,"Destroy Service")
    }
}