package com.free.imagefilter.tool

import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import java.io.File

class PhotoTool private constructor() {

    companion object {

        private var photoTool: PhotoTool? = null

        fun getInstance(): PhotoTool {
            if (photoTool == null) {
                @Synchronized
                if (photoTool == null) {
                    photoTool = PhotoTool()
                }

            }
            return photoTool as PhotoTool
        }

        fun destroy() {
            photoTool = null
        }
    }

    private val photoList by lazy {
        mutableListOf<String>()
    }

    private val resultList by lazy {
        mutableListOf<String>()
    }

    private var isFind = true

    //获取所有图片
    fun findAllPhoto(context: Context, photoCallBack: PhotoCallBack) {
        Thread(FindPhotoThr(context, photoCallBack)).start()
    }

    private inner class FindPhotoThr(val context: Context, val photoCallBack: PhotoCallBack) :
        Runnable {

        override fun run() {
            try {
                Log.d("LJW", "11")
                if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
                    return
                }
                photoList.clear()

                val imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                val contentResolver = context.contentResolver
                val cursor = contentResolver.query(
                    imageUri, null,
                    MediaStore.Images.Media.MIME_TYPE + " = ? or " + MediaStore.Images.Media.MIME_TYPE + " = ? ",
                    arrayOf("image/jpeg", "image/png"), MediaStore.Images.Media.DATE_MODIFIED
                )

                if (cursor != null) {
                    while (cursor.moveToNext() && isFind) {
                        val path =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
                                ?: continue

                        val file = File(path)

                        if (!file.exists()) {
                            continue
                        }

                        if (file.length() == 0L) {
                            continue
                        }

                        photoList.add(file.absolutePath)
                    }
                    photoList.reverse()
                    resultList.addAll(photoList)
                    Log.d("LJW", "22")
                    photoCallBack.callBack(resultList)
                    cursor.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getPhotos(): MutableList<String> {
        return resultList
    }

    interface PhotoCallBack {
        fun callBack(photoList: MutableList<String>)
    }

}