package com.module.personcenter.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.module.personcenter.R
import com.module.personcenter.databinding.PersonCenterListBinding

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 * 用于加载内容的适配器
 * @author longyanghe
 * @date 2022-01-17
 */
class PersonCenterListAdapter(
    @LayoutRes layoutResId: Int,
    data: MutableList<String>? = null,
    isPhone: Boolean
) :
    BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {
    var isPhoneDevice = true
    init {
        isPhoneDevice = isPhone
    }
    override fun convert(holder: BaseViewHolder, item: String) {
        val binding = PersonCenterListBinding.bind(holder.itemView)
        holder.setText(R.id.item_tv,item)
        if(!isPhoneDevice){
            binding.itemTv.setCompoundDrawables(null,null,null,null)
            if(item.contentEquals(context.getString(R.string.person_center_account_setting))){
                holder.itemView.background = context.getDrawable(R.color.text_no_select_color)
            }
        }
    }
}