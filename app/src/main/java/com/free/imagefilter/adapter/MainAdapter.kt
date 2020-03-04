package com.free.imagefilter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.free.imagefilter.R

class MainAdapter(val context: Context) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var requestTime = 0L

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.main_recyclerview_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
//        holder.setData(DataTool.getInstance().getData(position).imageId)
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val main_adapter_iv = itemView.findViewById<ImageView>(R.id.main_adapter_iv)

        fun setData(imageUrl: Int) {

//            main_adapter_iv.setOnClickListener {
//                val intent = Intent(context, ContentActivity::class.java)
//                intent.putExtra("url", imageUrl)
//                context.startActivity(intent)
//            }

//            Glide
//                .with(context)
//                .load(imageUrl)
//                .placeholder(R.mipmap.defult_img)
//                .override((getScreen().x / 2.0F).toInt(), (getScreen().y / 2.0F).toInt())
//                .centerCrop()
//                .addListener(object : RequestListener<Drawable> {
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        //Logger.e("$imageUrl : ${e?.toString()!!}")
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        return false
//                    }
//
//                })
//                .into(main_adapter_iv)
        }
    }


}