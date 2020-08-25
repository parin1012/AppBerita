package com.udacoding.appberita.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.udacoding.appberita.R
import com.udacoding.appberita.detail.DetailBeritaActivity
import com.udacoding.appberita.model.Berita
import kotlinx.android.synthetic.main.item_berita.view.*

class BeritaAdapter(var articles: ArrayList<Berita>?) : RecyclerView.Adapter<BeritaAdapter.BeritaHolder>() {
    class BeritaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.imageView
        val author = itemView.tvAuthor
        val date = itemView.tvPublishAt
        val title = itemView.tvTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_berita,parent,false)
        val holder = BeritaHolder(view)

        return holder
    }

    override fun onBindViewHolder(holder: BeritaHolder, position: Int) {
        holder.author.text = "Author : " + articles?.get(position)?.author
        holder.date.text = "Date : " + articles?.get(position)?.publishedAt
        holder.title.text = articles?.get(position)?.title

        Glide.with(holder.itemView.context).load(articles?.get(position)?.urlToImage).into(holder.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailBeritaActivity::class.java)
            intent.putExtra("title", articles?.get(position)?.title)
            intent.putExtra("desc", articles?.get(position)?.description)
            intent.putExtra("img",articles?.get(position)?.urlToImage)
            intent.putExtra("author",articles?.get(position)?.author)
            intent.putExtra("date",articles?.get(position)?.publishedAt)
            intent.putExtra("content",articles?.get(position)?.content)
            intent.putExtra("url",articles?.get(position)?.url)
            holder.itemView.context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }
}