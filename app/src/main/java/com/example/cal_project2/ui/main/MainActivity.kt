package com.example.cal_project2.ui.main

import android.os.Bundle
import com.example.cal_project2.databinding.ActivityMainBinding
import com.example.cal_project2.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun getViewBinding() =
        ActivityMainBinding.inflate(layoutInflater)
}