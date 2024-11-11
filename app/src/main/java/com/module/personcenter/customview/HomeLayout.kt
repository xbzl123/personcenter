package com.module.personcenter.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.module.personcenter.R
import com.module.personcenter.databinding.AppLogFragmentBinding
import com.module.personcenter.databinding.DeviceLogFragmentBinding

/**
 * Copyright (c) 2023 Raysharp.cn. All rights reserved
 *
 * HomeLayout
 * @author longyanghe
 * @date 2023-02-01
 */
class HomeLayout(context: Context, attrs: AttributeSet? = null) : ContentView(context, attrs) {

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.test_layout,this)
//        val binding = AppLogFragmentBinding.bind(root)
//        binding.btnUploadLog.setOnClickListener{
//            setUploadLog?.invoke()
//        }
    }
    var setUploadLog:(()->Unit)? = null
        set(value) {
            field = value
        }
}