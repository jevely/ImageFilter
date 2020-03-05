package com.free.imagefilter.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.free.imagefilter.R
import com.free.imagefilter.activity.DealActivity
import com.free.imagefilter.getScreen
import com.free.imagefilter.tool.PhotoTool
import java.io.File

class MainAdapter(val context: Context, val showList: MutableList<String>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.main_recyclerview_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return PhotoTool.getInstance().getPhotos().size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setData(showList[position])
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val main_adapter_iv = itemView.findViewById<ImageView>(R.id.main_adapter_iv)

        fun setData(imagePath: String) {

            main_adapter_iv.setOnClickListener {
                val intent = Intent(context, DealActivity::class.java)
                intent.putExtra("path", imagePath)
                context.startActivity(intent)
            }

            Glide
                .with(context)
                .load(File(imagePath))
                .placeholder(R.mipmap.ic_launcher)
                .override((getScreen().x / 4.0F).toInt(), (getScreen().x / 4.0F).toInt())
                .centerCrop()
                .into(main_adapter_iv)
        }
    }


}