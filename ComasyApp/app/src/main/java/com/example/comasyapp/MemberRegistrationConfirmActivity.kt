package com.example.comasyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MemberRegistrationConfirmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_registration_confirm)

        // 前の画面からメールアドレスを受け取る
        val mail_address = intent.getStringExtra("mail_address").toString()

        Log.i("メールアドレス", mail_address)

        // 前の画面からニックネームを受け取る
        val user_name = intent.getStringExtra("user_name").toString()

        Log.i("ニックネーム", user_name)

        // 前の画面からパスワードを受け取る
        val password = intent.getStringExtra("password").toString()

        Log.i("パスワード", password)
    }
}