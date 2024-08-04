package mr.mohammadi.videoplayer.presenter

import mr.mohammadi.videoplayer.ext.SplashModel
import mr.mohammadi.videoplayer.ext.SplashView

class SplashPresenter(private val view: SplashView, private val model: SplashModel) {
    fun checkLoginStatus() {
        if (model.isConnected()) {
            if (model.isUserLoggedIn()) {
                view.navigateToMain()
            } else {
                view.navigateToLogin()
            }
        } else {
            view.showNoInternetDialog()
        }
    }
}
