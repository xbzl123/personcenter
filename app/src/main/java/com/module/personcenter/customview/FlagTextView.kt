package com.module.personcenter.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import com.module.personcenter.R

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * FlagTextView 带椭圆下标的文字
 * @author longyanghe
 * @date 2022-01-24
 */
class FlagTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, textId: Int
) : androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    private var textHeight: Int = 0
    private var textWidth: Int = 0
    var childView: View? = null

    init {
        gravity = Gravity.CENTER_HORIZONTAL
        text = context.getText(textId)
        setTextSize(16f)
    }

    private var selectedColor = resources.getColor(R.color.authentication_text_blue_color)
        set(value) {
            selectedColor = value
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        textHeight = measuredHeight
        textWidth = measuredWidth
        setMeasuredDimension(textWidth, (textHeight*1.5f).toInt())
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = selectedColor
        paint.strokeWidth = 10f

        if(isSelected){
            setTextColor(selectedColor)
            canvas?.drawRoundRect(RectF(textWidth.toFloat()/2-30,textHeight.toFloat()+10
                ,textWidth.toFloat()/2+30,textHeight.toFloat()+20),
                30f,30f,paint)
        }else{
            setTextColor(resources.getColor(R.color.text_no_select_color))
        }
    }
}