package com.free.imagefilter.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.free.imagefilter.R
import com.free.imagefilter.tool.FilterTool
import com.free.imagefilter.tool.GPUImageFilterTools
import jp.co.cyberagent.android.gpuimage.GPUImageView
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter

class FilterAdapter(
    val context: Context,
    val filterClickCallBack: FilterClickCallBack
) :
    RecyclerView.Adapter<FilterAdapter.FilterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterAdapter.FilterViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.filter_adapter_layout, parent, false)
        return FilterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 15
    }

    override fun onBindViewHolder(holder: FilterAdapter.FilterViewHolder, position: Int) {
        holder.setData(FilterTool.getFilter(position), position)
    }

    inner class FilterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val gpuimage = itemView.findViewById<ImageView>(R.id.gpuimage)

        fun setData(filter: GPUImageFilter, position: Int) {

            gpuimage.setOnClickListener {
                filterClickCallBack.filterClickCallBack(filter)
            }

            gpuimage.setImageResource(FilterTool.getShowImg(position))

        }
    }

    interface FilterClickCallBack {
        fun filterClickCallBack(filter: GPUImageFilter)
    }
}