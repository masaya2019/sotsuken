package com.example.comasyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 新規会員登録はこちらボタン（transitionMemberRegistrationButton）をクリックしたら、
        transitionMemberRegistrationButton.setOnClickListener {
            // 新規会員登録画面（MemberRegistrationActivity.kt）へ遷移する
            val intent = Intent(this, MemberRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}