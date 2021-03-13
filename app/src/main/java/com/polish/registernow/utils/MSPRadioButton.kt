package com.polish.registernow.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatRadioButton

class MSPRadioButton(context:Context, attrs:AttributeSet):AppCompatRadioButton(context, attrs) {
    /**
     * The init block runs every time the class is instantiated.
     */
    init {
        applyFont()
    }
    /**
     * Applies a font to a Radio Button.
     */
    private fun applyFont(){
        val typeface:Typeface =
            Typeface.createFromAsset(context.assets, "")
        setTypeface(typeface)
    }

}