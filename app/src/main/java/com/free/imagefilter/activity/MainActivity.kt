package com.free.imagefilter.activity

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.free.imagefilter.BaseActivity
import com.free.imagefilter.R
import com.free.imagefilter.tool.PhotoTool
import com.free.imagefilter.view.TGridManager

class MainActivity : BaseActivity() {

    private lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        recyclerview = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = TGridManager(this, 4)

        PhotoTool.getInstance().findAllPhoto(this, object : PhotoTool.PhotoCallBack {
            override fun callBack(photoList: MutableList<String>) {
                Log.d("LJW", "finish size = ${photoList.size}")
            }
        })
    }

}
