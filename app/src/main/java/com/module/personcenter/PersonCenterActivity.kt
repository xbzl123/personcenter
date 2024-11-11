package com.module.personcenter

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 * 国家地区选择
 * @author longyanghe
 * @date 2022-01-11
 */
class PersonCenterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.person_center_activity)
    }
}