package com.cursokotlin.coupons.common.adapters

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.cursokotlin.coupons.R

@BindingAdapter("isGone")
fun bindIsGone (view: View, isGone: Boolean){
    view.visibility = if(isGone) View.GONE else View.VISIBLE
}

@BindingAdapter("textTitle")
fun bindTextTitle(view: TextView, isActive: Boolean){
    if(isActive){
        view.setText(R.string.main_text_title)
        view.setTextColor(Color.BLACK)
    }
    else{
        view.setText(R.string.main_title_disponible)
        view.setTextColor(Color.MAGENTA)
    }
}