package mr.mohammadi.videoplayer.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import mr.mohammadi.videoplayer.databinding.ActivitySplashBinding

class ViewSplashActivity (
    contextInstance: Context
): FrameLayout(contextInstance) {

    val binding = ActivitySplashBinding.inflate(LayoutInflater.from(context))


}