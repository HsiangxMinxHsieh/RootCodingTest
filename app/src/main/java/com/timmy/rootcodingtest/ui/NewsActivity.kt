package com.timmy.rootcodingtest.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.timmy.rootcodingtest.R
import com.timmy.rootcodingtest.databinding.ActivityNewsBinding
import com.timmy.rootcodingtest.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by lazy { ViewModelProvider(this).get(NewsViewModel::class.java) }

    private lateinit var mBinding: ActivityNewsBinding

    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news)

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
//            Timber.d("MainActivity中收到資料！大小是=>${it.size}")
            adapter.list = it
            adapter.notifyDataSetChanged()
        })
        mBinding.rvNews.adapter = adapter
        mBinding.lifecycleOwner = this

    }

}