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
            // パスワードが4文字以上 かつ ニックネームが入力されている　かつ 認証コードが数字かつ4文字
            if (checkNickname() && checkPassword() && checkToken()) {
                Log.i("テスト" , "ok")

                // 前の画面からメールアドレスを受け取る
                val mail_address = intent.getStringExtra("mail_address")

                // 認証コードを取得し、数字に変換
                val inputToken = inputToken.text.toString().toInt()

                // メールアドレスと認証コードをチェックするAPIにリクエストを投げる
                //　まだそのAPIは出来ていない...
            }
        }
    }

    // ニックネームが入力されているか？
    private fun checkNickname(): Boolean {
        // ニックネームが入力されている
        return if (inputUserName.text.toString().isNotEmpty()) {
            errorText.text = "ニックネームok"

            true
            // ニックネームが入力されている
        } else {
            errorText.text = "ニックネームを入力してください！"

            false
        }
    }

    // パスワードが4文字以上かつ一致するか？
    private fun checkPassword(): Boolean {
        // 入力されたパスワードを取得
        val inputPassword = inputPassword.text.toString()

        // 入力されたパスワード（再）を取得
        val inputReEnterPassword = inputReEnterPassword.text.toString()

        Log.i("入力されたパスワード", inputPassword)
        Log.i("入力されたパスワード（再）", inputReEnterPassword)

        // 入力されたパスワードと入力されたパスワード（再）が4文字以下
        if (inputPassword.length < 4 || inputReEnterPassword.length < 4) {
            errorText.text = "パスワードの文字数が不足しています！"

            return false

            // 入力されたパスワードと入力されたパスワード（再）が等しいかつ4文字以上
        } else if (inputPassword == inputReEnterPassword && inputPassword.length >= 4 && inputReEnterPassword.length >= 4) {
            errorText.text = "パスワード一致！"

            return true

            // パスワードが不一致
        } else {
            errorText.text = "パスワードが一致しません！\nもう一度入力してください！"

            return false
        }
    }

    // 認証コードが数字かつ4文字
    private fun checkToken(): Boolean {
        // 入力された認証コードを取得
        val inputToken = inputToken.text.toString()
        // 文字数が4文字か？
        when {
            // 認証コードが4文字
            inputToken.length == 4 -> {
                errorText.text = "認証コードが入力されています！"
                return true
            }
            // 認証コードが未入力
            inputToken.isEmpty() -> {
                errorText.text = "認証コードが入力されていません！\n認証コードを入力してください！"
                return false
            }
            // 認証コードの文字数が異なる
            else -> {
                errorText.text = "認証コードは4文字です！\nもう一度入力してください！"
                return false
            }
        }
    }

    // メールアドレスと認証コードをチェックするAPIにリクエストを投げる

}