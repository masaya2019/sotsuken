package com.example.comasyapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerViewAdapter(list : MutableList<OneItemMemoTitle>): RecyclerView.Adapter<ViewHolder>() {
    private val list:MutableList<OneItemMemoTitle> = list
    // リスナー格納変数
    private var listener: ((Long?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.one_item_memo_title,parent,false)
        val viewholder = ViewHolder(view)
        return viewholder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.textDatetime?.text = item.textDatetime
        holder.textMemoTitle?.text = item.textMemoTitle
        holder.textAddress?.text = item.textAddress
        holder.itemView.setOnClickListener {

            val context=holder.itemView.context
            // メモ詳細画面(MemoTextActivity.kt)へ遷移する
            val intent = Intent(context, MemoTextActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            // メールアドレスを渡す
            intent.putExtra("mail_address", item.textAddress)
            // 日付を渡す
            intent.putExtra("datetime", item.textDatetime)
            context.startActivity(intent)
        }
    }

    // リスナー
    fun setOnItemClickListener(listener: (Long?) -> Unit){
        this.listener = listener
    }
}