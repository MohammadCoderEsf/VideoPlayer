package mr.mohammadi.videoplayer.presenter

import android.app.Activity
import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.ext.AllVideoWatched
import mr.mohammadi.videoplayer.recyclerView.DataCurseVideos
import mr.mohammadi.videoplayer.recyclerView.RecyclerCurseVideos
import mr.mohammadi.videoplayer.view.VideosView

class VideosPresenter(private val view: VideosView, private val activity: Activity) {

    private val dataMoreVideos = arrayOf(
        DataCurseVideos("php چیست", R.drawable.img_curses_git),
        DataCurseVideos("ورژن های php", R.drawable.img_curses_git),
        DataCurseVideos("php usecase", R.drawable.img_curses_git),
        DataCurseVideos("نیازمندی های php", R.drawable.img_curses_git)
    )

    fun checkAllVideosWatched() {
        val allWatched = AllVideoWatched.areAllVideosWatched(activity)
        if (allWatched) {
            view.showEndCursesImage()
        } else {
            view.hideEndCursesImage()
        }
    }

    fun setupRecyclerMoreVideos() {
        val adapter = RecyclerCurseVideos(activity, dataMoreVideos)
        view.setRecyclerMoreVideos(adapter)
    }
}
