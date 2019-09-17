package ru.savitskiy.testtask

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.savitskiy.testtask.utils.isEmailValid
import ru.savitskiy.testtask.utils.isPassValid
import javax.inject.Inject

class LoginViewModel @Inject constructor(): ViewModel() {
    var login = MutableLiveData<String>()
    var pass = MutableLiveData<String>()
    var showErrorLogin = Transformations.map(login) {
        if (it.isEmailValid() or it.isEmpty()) null else
            App.appComponent.getContext().getString(R.string.check_login)
    }
    var showErrorPass = Transformations.map(pass) {
        if (it.isPassValid() or it.isEmpty()) null else
            App.appComponent.getContext().getString(R.string.check_pass)
    }
    var dataIsOk = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(showErrorLogin) {
            value = it == null && showErrorPass.value == null
        }
        addSource(showErrorPass) {
            value = it == null && showErrorLogin.value == null
        }
    }

    fun goToAuthZone() {
        if (dataIsOk.value == true) {
            // go
        }
    }

    fun goBack() {

    }
}