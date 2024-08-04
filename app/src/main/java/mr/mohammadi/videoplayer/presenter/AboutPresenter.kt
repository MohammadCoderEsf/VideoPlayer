package mr.mohammadi.videoplayer.presenter

import mr.mohammadi.videoplayer.ext.AboutContract

class AboutPresenter(private val view: AboutContract.View) : AboutContract.Presenter {

    override fun onInstagramClicked() {
        view.openInstagram()
    }

    override fun onYouTubeClicked() {
        view.openYouTube()
    }
}
