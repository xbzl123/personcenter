package com.module.personcenter.adapter

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.module.personcenter.R
import com.module.personcenter.databinding.UploadLogCamerasListBinding

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * CameraNameListAdapter
 * @author longyanghe
 * @date 2022-01-25
 */
/**
 * 检查是否除了第一位全部后面元素包含没有选定的情况
 */
fun checkItemIsNotAllSelected(selectStateList: ArrayList<CameraInfo>):Boolean {
    var temp = arrayListOf<CameraInfo>()
    temp.addAll(selectStateList)
        temp.apply {
            this.map {
                if(!it.isSelect){
                    return true
                }
            }
        }
    return false
}

/**
 * 包装类 CameraName
 */
data class CameraInfo(val name:String, var isSelect:Boolean)

/**
 * Devices Logs Camera info适配器
 */
class CameraInfoListAdapter(layoutResId: Int, cameraNameList: ArrayList<CameraInfo>) :BaseQuickAdapter<CameraInfo,BaseViewHolder>(layoutResId,cameraNameList){

    override fun convert(holder: BaseViewHolder, item: CameraInfo) {
        val binding = UploadLogCamerasListBinding.bind(holder.itemView)
        holder.setText(R.id.camera_name,item.name)
//        if(holder.absoluteAdapterPosition == 0){
//            binding.cameraImage.visibility = View.GONE
//        }
        binding.checkboxCameraName.isChecked = item.isSelect
    }
}