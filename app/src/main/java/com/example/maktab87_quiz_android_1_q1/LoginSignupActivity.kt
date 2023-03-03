package com.example.maktab87_quiz_android_1_q1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginSignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val manager = PreferencesManager(this)
        if (!manager.isFirstRun()) {
            val intent = Intent(this, TaskManagementActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(R.layout.activity_login_signup)
    }
}