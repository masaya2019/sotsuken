package com.example.comasyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColumnCustomAdapter(private val columnList: ArrayList<Columns>): RecyclerView.Adapter<ColumnCustomAdapter.ViewHolder>() {

    //Viewの初期化
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        //定義
        val title: TextView

        //つなげたレイアウトxmlのidを探す
        init {
             title = view.findViewById(R.id.columnRecycleView)
        }
    }


    //レイアウトの設定
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder{

        //レイアウトxmlとつなげる
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.activity_column, viewGroup, false)

        //上のViewHolderクラスに返す
        return ViewHolder(view)
    }


    //Viewの設定
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val columns = columnList[position]

        viewHolder.title.text = columns.column_title
    }

    // 表示数を返す_最大まで表示
    override fun getItemCount() = columnList.size
}