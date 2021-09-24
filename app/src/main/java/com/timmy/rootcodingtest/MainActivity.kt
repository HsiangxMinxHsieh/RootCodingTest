package com.timmy.rootcodingtest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.timmy.rootcodingtest.databinding.ActivityMainBinding
import com.timmy.rootcodingtest.ui.NewsAdapter
import com.timmy.rootcodingtest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initData()

        initView()
    }

    private fun initData() {
        viewModel.getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        adapter = NewsAdapter()

        viewModel.getLiveDataInRealm().observe(this, {
            Timber.d("MainActivity中收到資料！大小是=>${it.size}")
            adapter.list = it
            adapter.notifyDataSetChanged()
        })
        mBinding.rvNews.adapter = adapter
        mBinding.lifecycleOwner = this

    }

}