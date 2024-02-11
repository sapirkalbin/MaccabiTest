package com.axppress.maccabitest.ui.customviews

import android.content.Context
import android.util.AttributeSet
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.axppress.maccabitest.R


class WaveLoader(context: Context?, attrs: AttributeSet) : LottieAnimationView(context, attrs) {
    init {
        if (!isInEditMode) {
            setAnimation(R.raw.loader)
            repeatCount = LottieDrawable.INFINITE

            playAnimation()
        }
    }
}