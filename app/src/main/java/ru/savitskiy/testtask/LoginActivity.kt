package ru.savitskiy.testtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.login_activity.*
import ru.savitskiy.testtask.databinding.LoginActivityBinding
import ru.savitskiy.testtask.utils.CustomTransformationMethod

class LoginActivity : AppCompatActivity() {
    lateinit var binding: LoginActivityBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(LoginViewModel::class.java)
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
        buttonEntry.setOnClickListener { viewModel.goToAuthZone() }
    }
}
