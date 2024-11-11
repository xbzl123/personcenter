package com.module.personcenter.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import com.module.personcenter.R
import com.module.personcenter.adapter.CameraInfo
import com.module.personcenter.databinding.SelectItemTapLayoutBinding
import kotlin.math.absoluteValue

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * SelectItemTabLayout 包含2个自定义标题切换的布局容器，可以增减
 * @author longyanghe
 * @date 2022-01-24
 */
class SelectItemTabLayout(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var devicesLogLayout: DeviceLogLayout
    private var homeLayout: HomeLayout
    private var appLogLayout: AppLogLayout
    private var binding:SelectItemTapLayoutBinding

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.select_item_tap_layout, this)
        binding = SelectItemTapLayoutBinding.bind(root)

        homeLayout = HomeLayout(context!!,null)
        appLogLayout = AppLogLayout(context!!,null)
        devicesLogLayout = DeviceLogLayout(context!!,null)
        addViewAndFlag(homeLayout,R.string.account_setting_sign_out)
        addViewAndFlag(appLogLayout,R.string.person_center_app_logs)
        addViewAndFlag(devicesLogLayout,R.string.person_center_devices_logs)

    }

    fun addViewAndFlag(view: ContentView, content: Int){
        val flagTextView = FlagTextView(context!!,null,content)
        view.flagText = flagTextView
        addViewToContainer(flagTextView,view)
    }

    private fun addViewToContainer(flagTextView:FlagTextView, view: View){
        flagTextView.apply {
            binding.container.addView(view)
            binding.taps.addView(this)
            childView = view
            setOnClickListener {
                binding.taps.showSelectView(this)
            }
        }
    }

    fun loadData(cameraInfoList: ArrayList<CameraInfo>) {
        devicesLogLayout.initAdapter(cameraInfoList)
    }
    var appLogAct:(()->Unit)? = null
        set(value) {
            appLogLayout?.setUploadLog = value
        }

    var deviceLogAct:(()->Unit)? = null
        set(value) {
            devicesLogLayout?.setUploadLog = value
        }
}