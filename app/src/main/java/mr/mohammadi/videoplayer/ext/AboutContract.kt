package mr.mohammadi.videoplayer.ext

interface AboutContract {
    interface View {
        fun setGradientText()
        fun openInstagram()
        fun openYouTube()
    }

    interface Presenter {
        fun onInstagramClicked()
        fun onYouTubeClicked()
    }
}
