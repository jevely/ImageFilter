package com.free.imagefilter.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.free.imagefilter.BaseActivity
import com.free.imagefilter.R
import com.free.imagefilter.adapter.MainAdapter
import com.free.imagefilter.tool.PhotoTool
import com.free.imagefilter.view.TGridManager

class MainActivity : BaseActivity() {

    private lateinit var recyclerview: RecyclerView

    private lateinit var permission_re: RelativeLayout
    private lateinit var permission_bt: Button

    private val WRITE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE"
    private val READ_PERMISSION = "android.permission.READ_EXTERNAL_STORAGE"
    private val REQUEST_CODE = 0

    private var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        permission_bt = findViewById(R.id.permission_bt)
        permission_re = findViewById(R.id.permission_re)
        recyclerview = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = TGridManager(this, 4)

        permission_bt.setOnClickListener {
            checkPermission()
        }

        if (!checkPermission(this, WRITE_PERMISSION) || !checkPermission(this, READ_PERMISSION)
        ) {
            permission_re.visibility = View.VISIBLE
        } else {
            findPhoto()
        }

    }

    private fun findPhoto() {
        PhotoTool.getInstance().findAllPhoto(this, object : PhotoTool.PhotoCallBack {
            override fun callBack(photoList: MutableList<String>) {
                handler.sendEmptyMessage(0)
            }
        })
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            mainAdapter = MainAdapter(this@MainActivity, PhotoTool.getInstance().getPhotos())
            recyclerview.adapter = mainAdapter
        }
    }

    private fun checkPermission() {
        requestPermission(this, arrayOf(WRITE_PERMISSION, READ_PERMISSION), REQUEST_CODE)
    }

    override fun requestSuccess(requestCode: Int, permission: List<String>) {
        super.requestSuccess(requestCode, permission)
        if (permission.contains(WRITE_PERMISSION) && permission.contains(READ_PERMISSION)) {
            permission_re.visibility = View.GONE
            findPhoto()
        }
    }

    override fun requestError(requestCode: Int, permission: List<String>) {
        super.requestError(requestCode, permission)
    }

    override fun onDestroy() {
        super.onDestroy()
        PhotoTool.destroy()
    }

}
