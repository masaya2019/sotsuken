package com.example.comasyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
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

class ColumnActivity : AppCompatActivity(){

//    //カスタムadapterをadapterに設定
//    lateinit var cAdapter: ColumnCustomAdapter
//    //コラムクラスをColumnListに設定
//    lateinit var cColumnList: ArrayList<Columns>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_column)

        //　　コラムを追加するときは以下の部分（ナンバー）を追加する　3か所
        //  ここから

        //１．Buttonを参照
        val clmB1 = findViewById<Button>(R.id.clmB1)
        val clmB2 = findViewById<Button>(R.id.clmB2)
            //val clmBx = findViewById<Button>(R.id.clmBx)

        //２．Clm1FragmentActivityクラスをインスタンス化、その下も同様
        val clmF1 = ClmFragment1Activity()
        val clmF2 = ClmFragment2Activity()
            //val clmFx = ClmFragmentxActivity()

        //３．ButtonをクリックしたときにreplaceFragmentメソッドを実行
        clmB1.setOnClickListener {
            replaceFragment(clmF1)
        }
        clmB2.setOnClickListener {
            replaceFragment(clmF2)
        }
            //clmBx.setOnClickListener {
            //    replaceFragment(clmFx)
            //}

        //　ここまで


//        // データの作成(フラグメント使用のため以下は使わない)
          // ここから
//        val clm001 = Columns("No.1","冷蔵庫のお手入れ", R.drawable.icon_column)
//        val clm002 = Columns("No.2","冷蔵庫に入れる食材、入れない食材", R.drawable.icon_column)
//        cColumnList = arrayListOf(clm001,clm002)
//
//        // RecyclerViewの取得
//        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//
//        // LayoutManagerの設定
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        // CustomAdapterの生成と設定
//        cAdapter = ColumnCustomAdapter(cColumnList)
//        recyclerView.adapter = cAdapter
//
//        //インターフェースの実装(コラム一覧クリック)
//        cAdapter.setOnColumnClickListener(object :ColumnCustomAdapter.OnColumnClickListener{
//            override fun onColumnClickListener(view: View, position: Int, clickedText: Columns) {
//                val intent = Intent(applicationContext, HomeActivity::class.java)
//                    .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
//                startActivity(intent)
//            }
//        })
//
//        //区切り線
//        //val itemDecoration = DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
//        //columnRecycleView.addItemDecoration(itemDecoration)
//
//        //LayoutManager
//        //val layoutManager = LinearLayoutManager(this)
//        //columnRecycleView.layoutManager = layoutManager
          //ここまで使わない



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
    //R.id.containerに引数で渡されたフラグメントを入れる
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        //変更前↓
        //fragmentTransaction.replace(R.id.container, fragment)
        //変更後↓
        fragmentTransaction.add(R.id.container,fragment)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }

}