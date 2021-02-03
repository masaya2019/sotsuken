package com.example.comasyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemoTitleCustomAdapter(private val memoList: ArrayList<MemoTitle>): RecyclerView.Adapter<MemoTitleCustomAdapter.ViewHolder>() {

    // リスナー格納変数
    private lateinit var listener: OnItemClickListener

    // Viewの初期化
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val user_name: TextView
        val user_memo_title: TextView
        val post_datetime: TextView

        init {
            user_name = view.findViewById(R.id.textviewUserName)
            user_memo_title = view.findViewById(R.id.textviewMemoTitle)
            post_datetime = view.findViewById(R.id.textviewDatetime)
        }
    }

    // レイアウトの設定
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_one_memo_data, viewGroup, false)
        return ViewHolder(view)
    }

    // Viewの設定
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val memo = memoList[position]

        viewHolder.user_name.text = memo.user_name
        viewHolder.user_memo_title.text = memo.memo_title
        viewHolder.post_datetime.text = memo.post_datetime

        // タップしたとき
        viewHolder.itemView.setOnClickListener {
            listener.onItemClickListener(it, position, memo.user_mail_address, memo.post_datetime)
        }
    }

    // 表示数を返す
    override fun getItemCount() = memoList.size

    //インターフェースの作成
    interface OnItemClickListener{
        fun onItemClickListener(view: View, position: Int, mail_address: String, post_datetime: String)
    }

    // リスナー
    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

}