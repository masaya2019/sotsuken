package com.example.comasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 新規会員登録はこちらをクリックしたとき
        transitionMemberRegistrationButton.setOnClickListener {
            // 会員登録画面（MemberRegistrationActivity.kt）に遷移
            val intent = Intent(application, MemberRegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
