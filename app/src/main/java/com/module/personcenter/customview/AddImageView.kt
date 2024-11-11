package com.module.personcenter.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.module.personcenter.R
import android.graphics.*
import com.blankj.utilcode.util.ImageUtils

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * AddImageView
 * @author longyanghe
 * @date 2022-06-15
 */
class AddImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    init {
        setBackgroundDrawable(context.getDrawable(R.drawable.add_bg))
        setImageDrawable(context.getDrawable(R.drawable.ic_add))

    }

    override fun onDraw(canvas: Canvas?) {
//        setImageDrawable(context.getDrawable(R.drawable.ic_added))
        val paint = Paint()
        val decodeResource = BitmapFactory.decodeResource(context.resources, R.mipmap.test)
        //获取新的bitmap
        val bitmap = ImageUtils.toRoundCorner(ImageUtils.scale(decodeResource,measuredWidth,measuredHeight),15f)
        canvas?.drawBitmap(bitmap,0f,0f,paint)
        super.onDraw(canvas)
    }

}