package com.example.comasy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_member_registration.*

class MemberRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_registration)

        // 仮登録をクリックしたとき
        transitionMemberRegistrationFormButton.setOnClickListener {
            // mailAddressTextから入力されたメールアドレスを取得
            var mail_address = inputMailAddress.text
            // toastShow(mail_address.toString())





//            // 会員登録フォーム画面（MemberRegistrationFormActivity.kt）に遷移
//            val intent = Intent(application, MemberRegistrationActivity::class.java)
//            startActivity(intent)
        }
    }

    // デバッグ用
    fun toastShow(string : String){
        var toast = Toast.makeText(this, string, Toast.LENGTH_LONG)
        toast.show()
    }
}
