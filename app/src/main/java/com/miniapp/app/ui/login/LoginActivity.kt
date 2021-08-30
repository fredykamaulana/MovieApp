package com.miniapp.app.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
    private lateinit var callbackManager: CallbackManager
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    private val vm: LoginViewModel by viewModel()

    companion object{
        const val PUBLIC_PROFILE = "public_profile"
        const val EMAIL = "email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        callbackManager = CallbackManager.Factory.create()
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == Activity.RESULT_OK) loginEvent()
        }
        setupToolbar(binding.toolbar, "")

        setupObserver()
        setupView()
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupObserver() {
        vm.loginStatus.observe(this, {
            if(it) { binding.groupLoading.visibility = View.GONE
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            } else { binding.groupLoading.visibility = View.VISIBLE }
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
            btnFbLogin.setOnClickListener { fbSignIn() }
            btnGoogleLogin.setOnClickListener { googleSignIn() }
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

    private fun googleSignIn(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val client = GoogleSignIn.getClient(this, gso)
        //val account = GoogleSignIn.getLastSignedInAccount(this)

        val signInIntent = client.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun fbSignIn(){
        LoginManager.getInstance().logInWithReadPermissions(this, listOf(PUBLIC_PROFILE, EMAIL))
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(result: LoginResult?) { loginEvent() }

                override fun onCancel() { showSnackBar(binding.root,"Login Cancelled") }

                override fun onError(error: FacebookException?) { showSnackBar(binding.root, error?.message ?: "") }
            })
    }
}