package com.udacoding.appberita.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.udacoding.appberita.R
import kotlinx.android.synthetic.main.activity_detail_berita.*

class DetailBeritaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        val title =intent.getStringExtra("title")
        val author = intent.getStringExtra("author")
        val date = intent.getStringExtra("date")
        val desc = intent.getStringExtra("desc")
        val img = intent.getStringExtra("img")
        val content = intent.getStringExtra("content")
        val url = intent.getStringExtra("url")

        tvTitle.text = title
        tvAuthor.text = "Author : " + author
        tvPublishAt.text = "Post date : " + date
        tvContent.text =  content
        tvUrl.text = "URL : " + url

        Glide.with(this).load(img).into(imageView)


    }
}