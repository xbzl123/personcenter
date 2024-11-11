package com.module.personcenter.ui

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.DeviceUtils
import com.module.personcenter.R
import com.module.personcenter.adapter.PersonCenterListAdapter
import com.module.personcenter.databinding.PersonCenterFragmentBinding

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 * 个人中心页面
 * @author longyanghe
 * @date 2022-01-17
 */
class PersonCenterFragment : BaseViewBindingFragment<PersonCenterFragmentBinding>() {

    override fun inflateViewBinding(inflater: LayoutInflater): PersonCenterFragmentBinding {
        return PersonCenterFragmentBinding.inflate(inflater)
    }

    override fun initView() {
        findNavController().navigate(R.id.action_navigation_person_center_to_upload_log)

    }
}