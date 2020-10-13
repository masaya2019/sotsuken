package com.example.comasyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_member_registration.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.math.log


class MemberRegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_registration)

        // 仮登録ボタン（transitionMemberRegistrationFormActivityButton）をクリックしたら、
        transitionMemberRegistrationFormActivityButton.setOnClickListener {

            val handler = Handler()

            // 入力されたメールアドレスを取得
            val mailAddress = inputTextTextEmailAddress.text.toString()

//            Log.i("入力されたメールアドレス", mailAddress)

            // メールアドレスから認証コードを生成し、認証コードをメールで送るAPIにリクエストを投げる
            sendMailApi(mailAddress, handler, errorText)

//            Log.i("sendMailApi", "終了")
        }
    }


    // メールアドレスから認証コードを生成し、認証コードをメールで送るAPIにリクエストを投げる
    // 結果はjson形式で
    // アドレスが正常で、メールを送った場合　{ "status" :　"yes" }
    // すでに登録されているアドレスの場合　{ "status" :　"used_address_error" }
    // メールアドレスのに形式になっていない場合　{ "status" :　"address_type_error" }
    // を受け取る
    private fun sendMailApi(mail_address: String, handler: Handler, errorText: TextView) {

//        Log.i("sendMailApi", mail_address)

        val url = "http://10.0.2.2/sotsuken/api/pre_registration.php"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("mail_address", mail_address)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
//                json確認用（responseは一度しか受け取れないので、不便
//                val responseText:String = response.body()?.string().toString()
//                Log.i("test", responseText)

                // responseのstatusに対応する値（）を取得
                val jsonData = JSONObject(response.body()?.string())
                val apiStatus = jsonData.getString("status")

//                Log.i("apiStatus", apiStatus)

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  アドレスが正常で、メールを送った場合
                    "yes" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "認証コードを\n${mail_address}に送りました！"
                        }
                        // 会員登録画面詳細（MemberRegistrationFormActivity.kt）へ遷移する
                        val intent = Intent(applicationContext, MemberRegistrationFormActivity::class.java)
                        // メールアドレスを渡す
                        intent.putExtra("mail_address", mail_address)
                        startActivity(intent)
                    }
                    // すでに登録されているアドレスの場合
                    "used_address_error" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "${mail_address}は既に登録されています！\n別のアドレスを利用してください"
                        }
                    }
                    // メールアドレスのに形式になっていない場合
                    "address_type_error" -> {
                        // エラーを表示
                        handler.post {
                            errorText.text = "メールアドレスの形式ではありません！\nもう一度入力してください！"
                        }
                    }
                }
            }
        })
    }
}

// 参考URL
// OkHttpをPOSTで利用
// https://wiki.toridge.com/index.php?android-kotlin-okhttp3
// json受け取るとき
// https://qiita.com/minme31/items/a9636cb0453524c64e67