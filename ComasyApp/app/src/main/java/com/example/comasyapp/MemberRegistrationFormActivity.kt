package com.example.comasyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_member_registration_form.*

class MemberRegistrationFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_registration_form)

        // 次へボタン（transitionMemberRegistrationConfirmActivityButton）をクリックしたら、
        transitionMemberRegistrationConfirmActivityButton.setOnClickListener {
//            // 入力されたパスワードを取得
//            var inputPassword = inputPassword.text
//
//            // 入力されたパスワード（再）を取得
//            var inputReEnterPassword = inputReEnterPassword.text
//
//            Log.i("input", inputPassword.toString())
//            Log.i("input", inputReEnterPassword.toString())
//
//            if (inputPassword == inputReEnterPassword) {
//
//            }
        }
    }
}