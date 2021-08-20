package com.setianjay.myservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.setianjay.myservice.services.MyService

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var btnService: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initListener()
    }

    private fun initView(){
        btnService = findViewById(R.id.btn_service1)
    }

    private fun initListener(){
        btnService.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_service1 -> {
                startService(Intent(this@MainActivity, MyService::class.java))
            }
        }
    }
}