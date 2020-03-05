package com.free.imagefilter.tool

import android.content.Context
import android.graphics.PointF
import com.free.imagefilter.FilterApplication
import com.free.imagefilter.R
import jp.co.cyberagent.android.gpuimage.filter.*

object FilterTool {

    fun getFilter(position: Int): GPUImageFilter {
        return when (position) {
            1 -> GPUImageContrastFilter(2.0f)
            2 -> GPUImageGammaFilter(2.0f)
            3 -> GPUImageColorInvertFilter()
            4 -> GPUImageGrayscaleFilter()
            5 -> GPUImageSepiaToneFilter()
            6 -> GPUImageSobelEdgeDetectionFilter()
            7 -> GPUImageThresholdEdgeDetectionFilter()
            8 -> GPUImagePosterizeFilter()
            9 -> GPUImageFilterGroup(
                listOf(
                    GPUImageContrastFilter(),
                    GPUImageDirectionalSobelEdgeDetectionFilter(),
                    GPUImageGrayscaleFilter()
                )
            )
            10 -> GPUImageMonochromeFilter(
                1.0f, floatArrayOf(0.6f, 0.45f, 0.3f, 1.0f)
            )
            11 -> GPUImageVignetteFilter(
                PointF(0.5f, 0.5f),
                floatArrayOf(0.0f, 0.0f, 0.0f),
                0.3f,
                0.75f
            )
            12 -> GPUImageToneCurveFilter().apply {
                setFromCurveFileInputStream(
                    FilterApplication.getContext()!!.resources.openRawResource(
                        R.raw.tone_cuver_sample
                    )
                )
            }
            13 -> GPUImageLuminanceThresholdFilter(0.5f)
            14 -> GPUImageCrosshatchFilter()
            15 -> GPUImageCGAColorspaceFilter()
            else -> GPUImageKuwaharaFilter()
        }
    }

    fun getShowImg(position: Int): Int {
        return when (position) {
            1 -> R.mipmap.show_2
            2 -> R.mipmap.show_3
            3 -> R.mipmap.show_4
            4 -> R.mipmap.show_5
            5 -> R.mipmap.show_6
            6 -> R.mipmap.show_7
            7 -> R.mipmap.show_8
            8 -> R.mipmap.show_9
            9 -> R.mipmap.show_10
            10 -> R.mipmap.show_11
            11 -> R.mipmap.show_12
            12 -> R.mipmap.show_13
            13 -> R.mipmap.show_14
            14 -> R.mipmap.show_15
            15 -> R.mipmap.show_16
            else -> R.mipmap.show_1
        }
    }

}