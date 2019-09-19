package ru.savitskiy.testtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_activity.*
import ru.savitskiy.testtask.dagger.injectViewModel
import ru.savitskiy.testtask.databinding.LoginActivityBinding
import ru.savitskiy.testtask.utils.*

const val REQUEST_LOCATION_CODE = 777
class LoginActivity : AppCompatActivity() {
    lateinit var binding: LoginActivityBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(LoginViewModel::class.java)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.model = viewModel
        binding.lifecycleOwner = this
        toolbar.inflateMenu(R.menu.login_menu)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_create -> {
                    // do something
                    true
                }
                else -> false
            }
        }
        toolbar.setNavigationOnClickListener {
            viewModel.goBack()
        }
        passEditText.transformationMethod = CustomTransformationMethod()
        viewModel.showErrorLogin.observe(this, Observer {
            textInputLayout.error = it
        })
        viewModel.showErrorPass.observe(this, Observer {
            passInputLayout.error = it
        })
        viewModel.dataIsOk.observe(this, Observer {
            //fake observing
        })
        buttonEntry.setOnClickListener {
          checkPerms(android.Manifest.permission.ACCESS_COARSE_LOCATION, REQUEST_LOCATION_CODE) {
              fusedLocationClient.lastLocation.addOnSuccessListener {
                  viewModel.requestWeather(it.latitude, it.longitude)
              }
          }
        }
        viewModel.showSnackBarMessage.observe(this, EventObserver {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        })
        imageViewPassHint.setOnClickListener {
           alert(getString(R.string.pass_requirements))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_LOCATION_CODE -> {
                checkPermsResult(grantResults) {
                    fusedLocationClient.lastLocation.addOnSuccessListener {
                        viewModel.requestWeather(it.latitude, it.longitude)
                    }
                }
            }
        }
    }


}