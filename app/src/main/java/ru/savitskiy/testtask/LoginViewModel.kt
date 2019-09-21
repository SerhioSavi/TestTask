package ru.savitskiy.testtask

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import ru.savitskiy.testtask.utils.Event
import ru.savitskiy.testtask.utils.isEmailValid
import ru.savitskiy.testtask.utils.isPassValid
import ru.savitskiy.testtask.utils.toCelsius
import javax.inject.Inject

class LoginViewModel @Inject constructor(val weatherRepository: WeatherRepository): ViewModel() {
    var login = MutableLiveData<String>()
    var pass = MutableLiveData<String>()
    var showErrorLogin = Transformations.map(login) {
        if (it.isEmailValid()) null else
            App.appComponent.getContext().getString(R.string.check_login)
    }
    var showErrorPass = Transformations.map(pass) {
        if (it.isPassValid()) null else
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
    var showSnackBarMessage = MutableLiveData<Event<String>>()

    fun requestWeather(lat: Double, lon: Double) {
        if (dataIsOk.value == true) {
            weatherRepository.getWeather(lat.toString(),lon.toString(), {
                showSnackBarMessage.value = Event("${it?.name} ${it?.mainWeather?.temp?.toCelsius().toString()}")
            }, {

            })
        }
    }

    fun goBack() {

    }
}
