package com.module.personcenter.ui

import android.os.Bundle
import androidx.annotation.IdRes
/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 * 页面跳转内容类
 * @author longyanghe
 * @date 2022-01-11
 */
class ResultArgs {
    private val mArgsMap: MutableMap<String, Any> = HashMap()

    constructor(@IdRes recipientId: Int, requestCode: Int) {
        mArgsMap[RECIPIENT_ID] = recipientId
        mArgsMap[REQUEST_CODE] = requestCode
    }

    constructor(bundle: Bundle?) {
        if (null == bundle) {
            return
        }
        setBusinessArgs(bundle)
        mArgsMap[RECIPIENT_ID] = bundle.getInt(RECIPIENT_ID)
        mArgsMap[REQUEST_CODE] = bundle.getInt(REQUEST_CODE)
    }

    fun toBundle(): Bundle {
        val temp = Bundle()
        if (null != businessArgs) {
            temp.putAll(businessArgs)
        }
        temp.putInt(RECIPIENT_ID, recipientId)
        temp.putInt(REQUEST_CODE, requestCode)
        return temp
    }

    @get:IdRes
    val recipientId: Int
        get() = mArgsMap[RECIPIENT_ID] as Int
    val requestCode: Int
        get() = mArgsMap[REQUEST_CODE] as Int

    fun setBusinessArgs(businessArgs: Bundle?): ResultArgs {
        if (null == businessArgs) {
            return this
        }
        mArgsMap[BUNDLE] = businessArgs
        return this
    }

    private val businessArgs: Bundle?
        get() = mArgsMap[BUNDLE] as Bundle?

    companion object {
        private const val RECIPIENT_ID = "resultArgsRecipientId"
        private const val REQUEST_CODE = "ResultArgsRequestCode"
        private const val BUNDLE = "ResultArgsBundle"
    }
}