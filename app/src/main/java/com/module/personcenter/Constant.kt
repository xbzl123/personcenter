package com.module.personcenter

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved.
 *  存放常量
 *@author Liang Jingyan
 *@date 2022-01-22
 */
object Constant {
    //生物识别可用
    const val BIOMETRIC_SUCCESS = 0
    //生物识别未注册
    const val BIOMETRIC_ERROR_NONE_ENROLLED = 1
    //生物识别不可用
    const val BIOMETRIC_UNAVAILABLE = -1

    const val KEY_IS_FINGER = "isSetFinger"
}