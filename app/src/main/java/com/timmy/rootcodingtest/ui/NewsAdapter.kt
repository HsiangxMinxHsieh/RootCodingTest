package com.timmy.rootcodingtest.ui

import android.graphics.Bitmap
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.timmy.rootcodingtest.R
import com.timmy.rootcodingtest.database.NewsObj
import com.timmy.rootcodingtest.databinding.AdapterNewsBinding

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var list: MutableList<NewsObj> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            val item = list[position]
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    class ViewHolder private constructor(private val binding: AdapterNewsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsObj) {
            binding.news = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdapterNewsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

/**Glide圓角圖片設定*/
private val options by lazy {
    RequestOptions()
        .transform(MultiTransformation<Bitmap>(CenterCrop(), RoundedCorners(10)))
        .priority(Priority.NORMAL)
        .error(R.drawable.ic_baseline_error_outline)
}

@BindingAdapter("app:imageUrl")
fun bindImage(imageView: ImageView, url: String) {

    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_baseline_error_outline)
        .apply(options)
        .into(imageView)
}