package mr.mohammadi.videoplayer.presenter

import android.content.Context
import mr.mohammadi.videoplayer.ext.PlayFinalContract
import androidx.core.content.edit
import kotlin.math.ceil
import kotlin.math.min

class PlayFinalPresenter(private val context: Context) : PlayFinalContract.Presenter {
    private var view: PlayFinalContract.View? = null
    private var totalWatchedTime = 0L
    private var watchedSeconds = mutableSetOf<Int>()
    private var lastPosition = 0L
    private val prefs = context.getSharedPreferences("video_prefs", Context.MODE_PRIVATE)
    private var videoId: Int = -1



    override fun attachView(view: PlayFinalContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun onUpdateWatchedTime(currentPosition: Long) {
        val currentSecond = (currentPosition / 1000).toInt()
        if (currentSecond != lastPosition.toInt() && !watchedSeconds.contains(currentSecond)) {
            watchedSeconds.add(currentSecond)
            totalWatchedTime = watchedSeconds.size * 1000L
            saveWatchedSeconds()
            view?.showPlaybackProgress(getProgressPercentage())
        }
        lastPosition = currentPosition
    }

    fun getProgressPercentage(): Int {
        val videoDuration = view?.getVideoDuration()?.toDouble() ?: 1.0
        val progress = (totalWatchedTime.toDouble() / videoDuration) * 100
        return min(100.0, ceil(progress)).toInt()
    }



    private fun saveWatchedSeconds() {
        prefs.edit {
            putStringSet("watched_seconds_$videoId", watchedSeconds.map { it.toString() }.toSet())
        }
    }

    private fun saveWatchPercentage(percentage: Int) {
        prefs.edit {
            putInt("video_percentage_$videoId", percentage)
        }
    }

    override fun onPlayNext() {
        view?.let { view ->
            val videoList = view.getVideoList()
            val currentVideoIndex = view.getCurrentVideoIndex()
            if (currentVideoIndex < videoList.size - 1) {
                val nextVideoIndex = currentVideoIndex + 1
                val nextVideoId = videoList[nextVideoIndex]
                view.updateCurrentVideoIndex(nextVideoIndex)
                onLoadVideo(nextVideoId)
            } else {
                view.showToastMessage("شما در حال دیدن آخرین ویدیو این دوره هستید")
            }
        }
    }

    override fun onPlayPrevious() {
        view?.let { view ->
            val videoList = view.getVideoList()
            val currentVideoIndex = view.getCurrentVideoIndex()
            if (currentVideoIndex > 0) {
                val previousVideoIndex = currentVideoIndex - 1
                val previousVideoId = videoList[previousVideoIndex]
                view.updateCurrentVideoIndex(previousVideoIndex)
                onLoadVideo(previousVideoId)
            } else {
                view.showToastMessage("شما در حال دیدن اولین ویدیو این دوره هستید")
            }
        }
    }



    override fun onLoadVideo(videoId: Int) {
        this.videoId = videoId
        totalWatchedTime = 0L
        watchedSeconds.clear()
        lastPosition = 0L
        loadWatchedSeconds()

        view?.let { view ->
            view.loadVideoIntoPlayer(videoId)
        }
    }



    private fun loadWatchedSeconds() {
        prefs.getStringSet("watched_seconds_$videoId", emptySet())?.let { set ->
            watchedSeconds = set.map { it.toInt() }.toMutableSet()
            totalWatchedTime = watchedSeconds.size * 1000L
        }
    }

    override fun onPlaybackCompleted() {
        view?.let { view ->
            val progress = getProgressPercentage()
            if (progress >= 99) {
                saveWatchPercentage(100)
                view.showCompletion()
            }
        }
    }

    fun getTotalWatchedTime(): Long {
        return totalWatchedTime
    }
}
