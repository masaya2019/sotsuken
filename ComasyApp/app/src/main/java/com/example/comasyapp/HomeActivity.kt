package com.example.comasyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.transitionMemberRegistrationActivityButton

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //下記処理はRegistrationClickActivityは未作成の為コメント化しています

        /*// 中身登録ボタン(transitionRegistrationClickActivityButton)をクリックしたら
        transitionRegistrationClickActivityButton.setOnClickListener {
            // 食材手打ち登録（RegistrationClickActivity.kt）へ遷移する
            val intent = Intent(this, RegistrationClickActivity::class.java)
            startActivity(intent)
        }*/

        // 写真登録（ImageRegistrationButton）をクリックしたら、
        ImageRegistrationButton.setOnClickListener {

            //カメラ開く
        }
        ImageChangeButton.setOnClickListener {

            //カメラ開く
        }




        }
    }
