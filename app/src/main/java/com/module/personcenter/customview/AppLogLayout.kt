package com.module.personcenter.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.module.personcenter.R
import com.module.personcenter.databinding.AppLogFragmentBinding

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * AppLogLayout
 * @author longyanghe
 * @date 2022-05-25
 */
class AppLogLayout(context: Context, attrs: AttributeSet? = null) : ContentView(context, attrs) {

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.app_log_fragment,this)
        val binding = AppLogFragmentBinding.bind(root)
        binding.btnUploadLog.setOnClickListener{
            setUploadLog?.invoke()
        }
    }
    var setUploadLog:(()->Unit)? = null
        set(value) {
            field = value
        }
}