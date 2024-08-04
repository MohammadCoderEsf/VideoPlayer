package mr.mohammadi.videoplayer.view

import mr.mohammadi.videoplayer.recyclerView.RecyclerCurseVideos

interface VideosView {
    fun showEndCursesImage()
    fun hideEndCursesImage()
    fun setRecyclerMoreVideos(adapter: RecyclerCurseVideos)
}
