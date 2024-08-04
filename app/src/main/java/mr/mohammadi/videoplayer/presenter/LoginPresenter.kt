package mr.mohammadi.videoplayer.presenter

import mr.mohammadi.videoplayer.ext.LoginContract

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {

    override fun onClearButtonClicked() {
        view.clearInput()
        view.hideError()
    }

    override fun onPhoneNumberChanged(phoneNumber: String) {
        var correctedPhoneNumber = phoneNumber

        // بررسی و تصحیح دو رقم اول
        if (phoneNumber.length > 2 && !phoneNumber.startsWith("09")) {
            correctedPhoneNumber = "09" + phoneNumber.substring(2)
            view.updatePhoneNumber(correctedPhoneNumber)
            view.showError()
        }

        if (correctedPhoneNumber.length == 11) {
            view.enableLoginButton(true)
        } else {
            view.enableLoginButton(false)
        }
    }

    override fun onLoginButtonClicked(phoneNumber: String) {
        if (phoneNumber.length == 11) {
            view.navigateToAuthentication(phoneNumber)
        } else {
            view.showError()
        }
    }
}
