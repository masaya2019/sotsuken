package com.example.comasyapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.transitionColumnButton
import kotlinx.android.synthetic.main.activity_home.transitionMemoButton
import kotlinx.android.synthetic.main.activity_home.transitionRefrigeratorButton
import kotlinx.android.synthetic.main.activity_home.transitionSearchButton
import kotlinx.android.synthetic.main.activity_home.transitionSettingButton
import kotlinx.android.synthetic.main.activity_setting.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SettingActivity : AppCompatActivity(), SelectChangeOrInviteDialog.NoticeSelectChangeOrInviteDialogListener, NoOtherRefrigeratorDialog.NoticeNoOtherRefrigeratorDialogListener, SelectNewRefrigeratorDialog.NoticeSelectNewRefrigeratorDialogListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        // 冷蔵庫の切り替え/招待ボタンが押されたとき
        changeButton.setOnClickListener {
            // SelectChangeOrInviteDialogを表示
            val dialog = SelectChangeOrInviteDialog()
            dialog.show(supportFragmentManager, "SelectChangeOrInviteDialog")
        }

        // ログアウトボタンが押されたとき
        logoutButton.setOnClickListener {
            // 本体からログイン情報（メールアドレスとパスワード）を削除
            deleteUserData()

            // ログイン画面（LoginActivity.kt）へ遷移
            val intent = Intent(this, LoginActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        // メニューバーをクリックしたときの処理
        transitionRefrigeratorButton.setOnClickListener {
            // Home画面(HomeActivity.kt)へ遷移
            val intent = Intent(this, HomeActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        transitionColumnButton.setOnClickListener {
            // コラム画面(ColumnActivity.kt)へ遷移
            val intent = Intent(this, ColumnActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        transitionSearchButton.setOnClickListener {
            // レシピ検索画面(RecipeSearchActivity.kt)へ遷移
            val intent = Intent(this, RecipeSearchActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        transitionMemoButton.setOnClickListener {
            // メモ画面(MemoActivity.kt)へ遷移
            val intent = Intent(this, MemoActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
        transitionSettingButton.setOnClickListener {
            // 設定画面(SettingActivity.kt)へ遷移
            val intent = Intent(this, SettingActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }

    // 本体からログイン情報（メールアドレスとパスワード）を削除
    // https://maku77.github.io/android/fw/shared-preference.html
    fun deleteUserData() {
        // ログイン情報削除用（削除しないとログインしたままになります）
        getSharedPreferences("my_password", Context.MODE_PRIVATE).edit().apply {
            clear()
            commit()
        }
    }

    // 冷蔵庫切り替え/招待ボタンを押したときの処理（選択された値による）
    override fun onSelectChangeOrInviteDialogClick(dialog: DialogFragment, which: Int) {
        Log.i("何する？", which.toString())

        when (which) {
            // 冷蔵庫を切り替える処理
            0 -> {
                // 切り替え可能な冷蔵庫を探すAPI
                searchCanChangeRefrigerator()
            }
            // 新しい冷蔵庫を追加する処理
            1 -> {
                // 新しい冷蔵庫を追加するAPI
                createNewRefrigerator()
            }
            // 冷蔵庫の名前を変更する処理
            2 -> {
                // Toastを表示
                Toast.makeText(this, "その処理まだできてないんですよー", Toast.LENGTH_LONG).show()
            }
            // 招待する処理
            3 -> {
                // Toastを表示
                Toast.makeText(this, "その処理まだできてないんですよー", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onSelectChangeOrInviteDialogNegativeClick(dialog: DialogFragment) {
    }

    // 新しい冷蔵庫を追加するAPI
    private fun createNewRefrigerator() {

        // 本体からメールアドレスを取得
        var pref = getSharedPreferences("my_password", Context.MODE_PRIVATE)
        val login_mail_address = pref.getString("mail_address", "").toString()

        // 本体からrefrigerator_idを取得
        pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        // ダミーデータ
        val url = "http://10.0.2.2/sotsuken/api/testdata1.json"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("refrigerator_id", now_refrigerator_id)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {

                // responseのstatusに対応する値（）を取得
                val jsonData = JSONObject(response.body()?.string())
                val apiStatus = jsonData.getString("status")

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  データベースに登録された場合
                    "yes" -> {
                        // 本体からメールアドレスと冷蔵庫IDを削除
                        getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE).edit().apply {
                            clear()
                            commit()
                        }
                        // 新しいrefrigerator_idを取得
                        val new_refrigerator_id = jsonData.getString("refrigerator_id")

                        // 本体に新しい冷蔵庫情報（メールアドレスと冷蔵庫ID）を保存
                        saveRefrigeratorData(login_mail_address, new_refrigerator_id)

                        // Home画面(HomeActivity.kt)へ遷移
                        val intent = Intent(applicationContext, HomeActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                    }
//                    // 以下はエラー用に仮作成
//                    // えらー
//                    "" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = ""
//                        }
//                    }
                }
            }
        })
    }

    // 本体に冷蔵庫情報（メールアドレスと冷蔵庫ID）を保存
    fun saveRefrigeratorData(mail_address: String, refrigerator_id: String) {
        getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE).edit().apply {
            putString("mail_address", mail_address)
            putString("refrigerator_id", refrigerator_id)
            commit()
        }
    }

    // 切り替え可能な冷蔵庫を探すAPI
    private fun searchCanChangeRefrigerator() {

        // 本体からメールアドレスを取得
        var pref = getSharedPreferences("my_password", Context.MODE_PRIVATE)
        val login_mail_address = pref.getString("mail_address", "").toString()

        // 本体からrefrigerator_idを取得
        pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val now_refrigerator_id = pref.getString("refrigerator_id", "").toString()

        val handler = Handler()

        // ダミーデータ
        val url = "http://10.0.2.2/sotsuken/api/testdata2.json"

        val body = FormBody.Builder(charset("UTF-8"))
            .add("mail_address", login_mail_address)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {

            // リクエスト結果受取に失敗
            override fun onFailure(call: Call, e: IOException) {}

            // リクエスト結果受取に成功
            override fun onResponse(call: Call, response: Response) {

                // 全体のJSONObjectをとる
                val jsonObj = JSONObject(response.body()?.string())

                // responseのstatusに対応する値（）を取得
                val apiStatus = jsonObj.getString("status")

                // responseのstatusによって次の画面に進むorエラーを表示する
                when (apiStatus) {

                    //  結果がyesなら
                    "yes" -> {
                        //　dataをjson配列に入れる
                        val datas = jsonObj.getJSONArray("data")

                        // 冷蔵庫が1つしかない時
                        if (datas.length() == 1) {

                            // NoOtherRefrigeratorDialogを表示
                            val dialog = NoOtherRefrigeratorDialog()
                            dialog.show(supportFragmentManager, "NoOtherRefrigeratorDialog")

                            // 冷蔵庫が１つ以上存在するとき
                        } else {

                            // 冷蔵庫IDを入れる配列
                            val refrigeratorDataArray: Array<String?> = arrayOfNulls(datas.length() - 1)

                            var j = 0

                            // データを1個づつ取り出す
                            for (i in 0 until datas.length()) {

                                // 1レコードをjsonObjectに入れる
                                val zeroJsonObj = datas.getJSONObject(i)

                                // 冷蔵庫ID
                                val refrigerator_id = zeroJsonObj.getString("refrigerator_id")

                                // 現在の冷蔵庫でなければ
                                if (refrigerator_id != now_refrigerator_id) {

                                    Log.i("r_id", refrigerator_id)

                                    // 冷蔵庫IDを配列に追加
                                    refrigeratorDataArray[j] = refrigerator_id
                                    j++
                                }
                            }
                            // 切り替え可能な冷蔵庫を表示
                            handler.post {
                                // Bundleのインスタンスを作成する
                                val bundle = Bundle()
                                // Key/Pairの形で値をセットする
                                bundle.putStringArray("KEY_DATA_ARRAY", refrigeratorDataArray)
                                // Fragmentに値をセットする
                                val dialog = SelectNewRefrigeratorDialog()
                                dialog.setArguments(bundle)
                                // SelectNewRefrigeratorDialogを表示
                                dialog.show(supportFragmentManager, "SelectNewRefrigeratorDialog")
                            }
                        }
                    }
//                    // 以下はエラー用に仮作成
//                    // えらー
//                    "" -> {
//                        // エラーを表示
//                        handler.post {
//                            errorText.text = ""
//                        }
//                    }
                }
            }
        })
    }

    override fun onNoOtherRefrigeratorDialogPositiveClick(dialog: DialogFragment) {
    }

    // 冷蔵庫を切り替える
    override fun onSelectNewRefrigeratorDialogClick(dialog: DialogFragment, new_refrigerator_id: String) {

        // 本体からメールアドレスと冷蔵庫IDを削除
        getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE).edit().apply {
            clear()
            commit()
        }

        // 本体からメールアドレスを取得
        var pref = getSharedPreferences("my_password", Context.MODE_PRIVATE)
        val login_mail_address = pref.getString("mail_address", "").toString()

        // 本体に新しい冷蔵庫情報（メールアドレスと冷蔵庫ID）を保存
        saveRefrigeratorData(login_mail_address, new_refrigerator_id)

        // Home画面(HomeActivity.kt)へ遷移
        val intent = Intent(applicationContext, HomeActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    override fun onSelectNewRefrigeratorDialogNegativeClick(dialog: DialogFragment) {
    }
}