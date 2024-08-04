package mr.mohammadi.videoplayer.presenter

import android.content.Context
import mr.mohammadi.videoplayer.ext.EvidenceModel
import mr.mohammadi.videoplayer.view.EvidenceView

// EvidencePresenter.kt
class EvidencePresenter(private val view: EvidenceView, private val model: EvidenceModel) {
    fun checkVideoStatus(context: Context) {
        val allWatched = model.areAllVideosWatched(context)
        if (allWatched) {
            view.showGiveEvidenceLayout()
        } else {
            view.showNoEvidenceLayout()
        }
    }

    fun onViewCourseButtonClicked() {
        view.navigateToMainActivity()
    }
}
