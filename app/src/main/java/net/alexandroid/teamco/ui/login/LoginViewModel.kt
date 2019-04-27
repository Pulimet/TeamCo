package net.alexandroid.teamco.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import net.alexandroid.teamco.ui.base.BaseViewModel

class LoginViewModel : BaseViewModel() {

    private lateinit var progressBar: MutableLiveData<Boolean>
    private lateinit var navigate: MutableLiveData<Unit>

    fun getProgressBar(): LiveData<Boolean> {
        if (!::progressBar.isInitialized) {
            progressBar = MutableLiveData()
        }
        return progressBar
    }

    fun getNavigate(): LiveData<Unit> {
        if (!::navigate.isInitialized) {
            navigate = MutableLiveData()
        }
        return navigate
    }

    fun onUserLoggedIn() {
        navigate.postValue(null)
    }

    fun onStartFireBaseLogin() {
        progressBar.postValue(true)
    }

    fun onFinishedFireBaseLogin() {
        progressBar.postValue(false)
    }


}