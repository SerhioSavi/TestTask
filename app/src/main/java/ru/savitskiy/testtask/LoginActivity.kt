package ru.savitskiy.testtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import ru.savitskiy.testtask.databinding.LoginActivityBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: LoginActivityBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = injectViewModel(LoginViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.login_activity)
        binding.model = viewModel
        binding.lifecycleOwner = this
    }
}
