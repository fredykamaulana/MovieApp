package com.miniapp.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.miniapp.app.R
import com.miniapp.app.databinding.ActivityLoginBinding
import com.miniapp.app.ui.MainActivity
import com.miniapp.app.ui.base.BaseActivity
import com.miniapp.app.ui.util.setupToolbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val vm: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupToolbar(binding.toolbar, "")

        setupObserver()
        setupView()
    }

    private fun setupObserver() {
        vm.loginStatus.observe(this, {
            if(it) {
                binding.groupLoading.visibility = View.GONE
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            } else {
                binding.groupLoading.visibility = View.VISIBLE
            }
        })
    }

    private fun setupView() {
        binding.run {
            btnLogin.setOnClickListener {
                val filled = binding.etUsername.text?.isNotEmpty() ?: false &&
                        binding.etPassword.text?.isNotEmpty() ?: false
                if(filled) loginEvent()
                else showSnackBar(binding.root, "Harap isi username dan password", "OK")
            }
            btnFbLogin.setOnClickListener {
                //LoginManager.getInstance().logInWithReadPermissions(this@LoginActivity, listOf("public_profile", "email"))
                showSnackBar(binding.root, "Fitur dalam pengembangan", "OK")
            }
            btnGoogleLogin.setOnClickListener {
                showSnackBar(binding.root, "Fitur dalam pengembangan", "OK")
            }
            btnLoginWithFingerprint.setOnClickListener {
                showSnackBar(binding.root, "Fitur dalam pengembangan", "OK")
            }
            tvForgotPassword.setOnClickListener {
                showSnackBar(binding.root, "Fitur dalam pengembangan", "OK")
            }
            tvRegisterAccount.setOnClickListener {
                showSnackBar(binding.root, "Fitur dalam pengembangan", "OK")
            }
        }
    }

    private fun loginEvent(){
        vm.loginStatus(false)
        runBlocking {
            launch {
                delay(1000)
                vm.loginStatus(true)
            }
        }
    }
}