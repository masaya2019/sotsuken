package com.example.comasyapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.one_item_memo_title.view.*

class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    var textDatetime: TextView? = null
    var textMemoTitle: TextView? = null
    var textAddress: TextView? = null

    init {
        textDatetime = itemView.textviewDate
        textMemoTitle = itemView.textviewMemoTitle
        textAddress = itemView.textviewAddress
    }
}