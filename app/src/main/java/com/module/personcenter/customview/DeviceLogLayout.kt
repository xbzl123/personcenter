package com.module.personcenter.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.ScrollView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.module.personcenter.R
import com.module.personcenter.adapter.CameraInfo
import com.module.personcenter.adapter.CameraInfoListAdapter
import com.module.personcenter.adapter.checkItemIsNotAllSelected
import com.module.personcenter.databinding.DeviceLogFragmentBinding

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * DeviceLogLayout
 * @author longyanghe
 * @date 2022-05-25
 */
    class DeviceLogLayout(context: Context, attrs: AttributeSet? = null) : ContentView(context, attrs) {

        private var binding: DeviceLogFragmentBinding

    init {
        val root = inflate(context,R.layout.device_log_fragment,this)
        binding = DeviceLogFragmentBinding.bind(root)
        binding.scrollView.setFillViewport(true)
        binding.btnAuthorize.setOnClickListener{
            setUploadLog?.invoke()
        }
    }

    var setUploadLog:(()->Unit)? = null
        set(value) {
            field = value
        }

    fun initAdapter(cameraInfoList:ArrayList<CameraInfo>){
        binding.cameraRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

            var cameraInfoListAdapter =
                CameraInfoListAdapter(R.layout.upload_log_cameras_list, cameraInfoList)
            cameraInfoListAdapter.addChildClickViewIds(R.id.checkbox_camera_name)
            cameraInfoListAdapter.setOnItemChildClickListener{
                    adapter,view,pos->
                val isChecked = (view as CheckBox).isChecked
                cameraInfoList[pos].isSelect = isChecked
                if(pos == 0){
                    cameraInfoList.map {
                        it.isSelect = isChecked
                    }
                    adapter.notifyDataSetChanged()
                }else{
                    cameraInfoList[0].isSelect = !checkItemIsNotAllSelected(cameraInfoList)
                    adapter.notifyItemChanged(0)
                }
            }
            adapter = cameraInfoListAdapter
        }
    }
}