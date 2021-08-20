package com.setianjay.myservice.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log

class MyBoundService : Service() {
    private val mBinder = MyBinder()
    private val startTime = System.currentTimeMillis()

    private val mTimer: CountDownTimer = object : CountDownTimer(100000,1000){
        override fun onTick(millisUntilFinished: Long) {
            Log.d(TAG, "System.currentTimeMillis : ${System.currentTimeMillis()}")
            val elapsedTime  = System.currentTimeMillis() - startTime
            Log.d(TAG, "elapsedTime: ${System.currentTimeMillis() - startTime}")
            Log.d(TAG, "onTick: $elapsedTime")
        }

        override fun onFinish() {

        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate running")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind running")
        Log.d(TAG, "startTime : $startTime")
        mTimer.start()
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnBind running")
        mTimer.cancel()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy running")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind running")
    }

    inner class MyBinder: Binder(){
        val getService get()  = this@MyBoundService
    }

    companion object{
        private val TAG = MyBoundService::class.java.simpleName
    }
}