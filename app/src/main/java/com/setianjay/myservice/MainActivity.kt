package com.setianjay.myservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.setianjay.myservice.services.MyBoundService
import com.setianjay.myservice.services.MyJobIntentService
import com.setianjay.myservice.services.MyService

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnService: Button
    private lateinit var btnJobIntentService: Button
    private lateinit var btnBoundService: Button
    private lateinit var btnStopBoundService: Button

    private var mServiceBound = false
    private lateinit var mBoundService: MyBoundService

    private val mServiceConnection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myBinder = service as MyBoundService.MyBinder
            mBoundService = myBinder.getService
            mServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
    }

    private fun initView() {
        btnService = findViewById(R.id.btn_service1)
        btnJobIntentService = findViewById(R.id.btn_service2)
        btnBoundService = findViewById(R.id.btn_service3)
        btnStopBoundService = findViewById(R.id.btn_service4)
    }

    private fun initListener() {
        btnService.setOnClickListener(this)
        btnJobIntentService.setOnClickListener(this)
        btnBoundService.setOnClickListener(this)
        btnStopBoundService.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_service1 -> {
                startService(Intent(this@MainActivity, MyService::class.java))
            }
            R.id.btn_service2 -> {
                Intent(this, MyJobIntentService::class.java).let {
                    it.putExtra(MyJobIntentService.EXTRA_DURATION, 5000L)
                    MyJobIntentService.enqueueWork(this, it)
                }
            }
            R.id.btn_service3 -> {
                bindService(
                    Intent(this@MainActivity, MyBoundService::class.java), mServiceConnection,
                    BIND_AUTO_CREATE)
            }
            R.id.btn_service4 -> {
                unbindService(mServiceConnection)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mServiceBound){
            unbindService(mServiceConnection)
        }
    }
}