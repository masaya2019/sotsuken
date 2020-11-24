package com.example.comasyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_column.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.transitionColumnButton
import kotlinx.android.synthetic.main.activity_home.transitionMemoButton
import kotlinx.android.synthetic.main.activity_home.transitionRefrigeratorButton
import kotlinx.android.synthetic.main.activity_home.transitionSearchButton
import kotlinx.android.synthetic.main.activity_home.transitionSettingButton
import kotlinx.android.synthetic.main.activity_memo.*

class ColumnActivity : AppCompatActivity() {

    //カスタムadapterをadapterに設定
    lateinit var cAdapter: ColumnCustomAdapter
    //コラムクラスをColumnListに設定
    lateinit var cColumnList: ArrayList<Columns>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_column)

        // データの作成
        val clm001 = Columns(1, "冷蔵庫のお手入れ", "冷蔵庫のお手入れ＜本文＞")
        val clm002 = Columns(2, "冷蔵庫に入れる食材、入れない食材", "冷蔵庫に入れる食材、入れない食材＜本文＞")
        cColumnList = arrayListOf(clm001,clm002)

        // RecyclerViewの取得
        val recyclerView = findViewById<RecyclerView>(R.id.columnRecycleView)

        // LayoutManagerの設定
        recyclerView.layoutManager = LinearLayoutManager(this)

        // CustomAdapterの生成と設定
        cAdapter = ColumnCustomAdapter(cColumnList)
        recyclerView.adapter = cAdapter




        //区切り線
        //val itemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
        //columnRecycleView.addItemDecoration(itemDecoration)

        //LayoutManager
        //val layoutManager = LinearLayoutManager(this)
        //columnRecycleView.layoutManager = layoutManager


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
}