package com.example.comasyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColumnCustomAdapter(private val columnList: ArrayList<Columns>): RecyclerView.Adapter<ColumnCustomAdapter.ViewHolder>() {

    //リスナー格納変数
    lateinit var listener: OnColumnClickListener

    // Viewの初期化
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val name: TextView
        val cNum: TextView

        init {
            image = view.findViewById(R.id.image)
            name = view.findViewById(R.id.name)
            cNum = view.findViewById(R.id.cNum)
        }
    }


    //レイアウトの設定
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):ViewHolder{
        //レイアウトxmlとつなげる
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.column_list_item, viewGroup, false)
        //上のViewHolderクラスに返す
        return ViewHolder(view)
    }


    //Viewの設定
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val column = columnList[position]

        viewHolder.image.setImageResource(column.imageId)
        viewHolder.name.text = column.name
        viewHolder.cNum.text = column.cNum.toString()

        //タップしたとき
        viewHolder.itemView.setOnClickListener{
            listener.onColumnClickListener(it,position,columnList[position])
        }
    }

    //インターフェースの作成
    interface OnColumnClickListener{
        fun onColumnClickListener(view: View,position: Int,clickedText: Columns)
    }

    //リスナー
    fun setOnColumnClickListener(listener: OnColumnClickListener){
        this.listener = listener
    }

    // 表示数を返す_最大まで表示
    override fun getItemCount() = columnList.size

}