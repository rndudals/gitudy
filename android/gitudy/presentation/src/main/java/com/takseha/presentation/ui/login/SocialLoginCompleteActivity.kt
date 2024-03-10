package com.takseha.presentation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.takseha.data.dto.Role
import com.takseha.presentation.R
import com.takseha.presentation.databinding.ActivitySocialLoginCompleteBinding
import com.takseha.presentation.ui.home.MainHomeActivity

class SocialLoginCompleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySocialLoginCompleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_login_complete)
        setBinding()

        binding.confirmBtn.setOnClickListener {
            val role = intent.getStringExtra("role").toString()
            controlRegisterView(role)
            Log.d("SocialLoginCompleteActivity", "role: $role")
        }
    }

    private fun setBinding() {
        binding = ActivitySocialLoginCompleteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun controlRegisterView(role: String) {
        if (Role.valueOf(role) == Role.UNAUTH || Role.valueOf(role) == Role.WITHDRAW) {
            startActivity(Intent(this, PopupAgreementActivity::class.java))
        } else {
            startActivity(Intent(this, MainHomeActivity::class.java))
        }
    }
}