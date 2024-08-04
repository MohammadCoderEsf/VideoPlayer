package mr.mohammadi.videoplayer.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.MediaController
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import mr.mohammadi.videoplayer.R
import mr.mohammadi.videoplayer.databinding.ActivityPlayFinalBinding
import mr.mohammadi.videoplayer.ext.DialogHelper
import kotlin.math.ceil
import kotlin.math.min
import mr.mohammadi.videoplayer.ext.PlayFinalContract
import mr.mohammadi.videoplayer.presenter.PlayFinalPresenter

class PlayFinalActivity : AppCompatActivity(), PlayFinalContract.View {
    private lateinit var presenter: PlayFinalPresenter
    private lateinit var binding: ActivityPlayFinalBinding
    private lateinit var videoList: IntArray
    private lateinit var videoTitles: Array<String>  // متغیر عضو برای لیست عناوین ویدیوها
    private var currentVideoIndex: Int = 0
    private var videoDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = PlayFinalPresenter(this)
        presenter.attachView(this)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                navigateToPreviousActivity()
            }
        })

        binding.icBack.setOnClickListener {
            navigateToPreviousActivity()
        }

        binding.supportTicket.setOnClickListener {
            DialogHelper.showSupportTicketDialog(this)
        }

        videoList = intent.getIntArrayExtra("videoList") ?: intArrayOf()
        currentVideoIndex = intent.getIntExtra("currentVideoIndex", 0)
        videoTitles = intent.getStringArrayExtra("videoTitles") ?: arrayOf()

        titlePosition()

        binding.btnNext.setOnClickListener {
            presenter.onPlayNext()
        }
        binding.btnPrevious.setOnClickListener {
            presenter.onPlayPrevious()
        }

        binding.seekBar.setOnTouchListener { _, _ -> true }

        if (videoList.isNotEmpty()) {
            presenter.onLoadVideo(videoList[currentVideoIndex])
        }

        val handler = Handler(mainLooper)
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    if (binding.videoViewFinal.isPlaying) {
                        presenter.onUpdateWatchedTime(binding.videoViewFinal.currentPosition.toLong())
                    }
                } catch (e: Exception) {
                    Log.e("EXCEPTION_UPDATE_TIMER", e.toString())
                }
                handler.postDelayed(this, 1000)
            }
        }, 0)
    }

    private fun titlePosition() {
        // دریافت عنوان بر اساس ایندکس فعلی ویدیو
        val titlePosition = videoTitles.getOrNull(currentVideoIndex)
        binding.txtTitleVideo.text = titlePosition ?: "عنوان نامشخص"
    }

    override fun updateCurrentVideoIndex(index: Int) {
        currentVideoIndex = index
        titlePosition()  // به‌روزرسانی عنوان بعد از تغییر ایندکس ویدیو
    }

    override fun showPlaybackProgress(progress: Int) {
        binding.text.text = "درصد پخش شده: $progress%"
        binding.seekBar.progress = progress
    }

    override fun showCompletion() {
        binding.text.text = "درصد پخش شده: 100%"
        binding.seekBar.progress = 100
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun getVideoList(): IntArray {
        return videoList
    }

    override fun getCurrentVideoIndex(): Int {
        return currentVideoIndex
    }

    override fun getVideoDuration(): Int {
        return videoDuration
    }

    override fun loadVideoIntoPlayer(videoId: Int) {
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + videoId)
        binding.videoViewFinal.setVideoURI(videoUri)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.videoViewFinal)
        binding.videoViewFinal.setMediaController(mediaController)

        binding.videoViewFinal.setOnPreparedListener {
            videoDuration = it.duration
            it.start()
        }

        binding.videoViewFinal.setOnCompletionListener {
            presenter.onPlaybackCompleted()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("videoList", videoList)
        outState.putInt("currentVideoIndex", currentVideoIndex)
        outState.putInt("videoDuration", videoDuration)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        videoList = savedInstanceState.getIntArray("videoList") ?: intArrayOf()
        currentVideoIndex = savedInstanceState.getInt("currentVideoIndex", 0)
        videoDuration = savedInstanceState.getInt("videoDuration", 0)
    }

    private fun navigateToPreviousActivity() {
        val intent = Intent(this, PlayVideoActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }

    private fun setupHandlers() {
        val handler = Handler(mainLooper)
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    if (binding.videoViewFinal.isPlaying) {
                        presenter.onUpdateWatchedTime(binding.videoViewFinal.currentPosition.toLong())
                        val progress = presenter.getProgressPercentage()
                        binding.text.text = "درصد پخش شده: ${progress}%"
                        binding.seekBar.progress = progress
                    }
                } catch (e: Exception) {
                    Log.e("EXCEPTION_UPDATE_TIMER", e.toString())
                }
                handler.postDelayed(this, 1000)
            }
        }, 0)

        binding.videoViewFinal.setOnCompletionListener {
            presenter.onPlaybackCompleted()
        }
    }

    override fun onResume() {
        super.onResume()
        setupHandlers()
        presenter.onLoadVideo(videoList[currentVideoIndex])
    }

    override fun onPause() {
        super.onPause()
        presenter.detachView()
    }

    companion object {
        fun allVideoWatched(context: Context, videoList: IntArray): Boolean {
            val prefs = context.getSharedPreferences("video_prefs", Context.MODE_PRIVATE)
            for (videoId in videoList) {
                val watchedPercentage = prefs.getInt("video_percentage_$videoId", 0)
                if (watchedPercentage < 100) {
                    return false
                }
            }
            return true
        }

        fun getVideoWatchPercentage(context: Context, videoId: Int): Int {
            val prefs = context.getSharedPreferences("video_prefs", Context.MODE_PRIVATE)
            return prefs.getInt("video_percentage_$videoId", 0)
        }
    }
}
