package mr.mohammadi.videoplayer.ext

import android.net.Uri

interface PlayVideoContract {
    interface View {
        fun showVideo(uri: Uri)
        fun updateFragment(fragment: androidx.fragment.app.Fragment)
        fun restoreVideoPosition(position: Int)
        fun showFragmentSelectionAnimation(isInformationSelected: Boolean)
    }

    interface Presenter {
        fun onVideoReady()
        fun onSaveVideoPosition(position: Int)
        fun onFragmentSelected(fragmentType: FragmentType)
        fun onActivityCreated()
    }

    enum class FragmentType {
        INFORMATION, VIDEOS
    }
}


