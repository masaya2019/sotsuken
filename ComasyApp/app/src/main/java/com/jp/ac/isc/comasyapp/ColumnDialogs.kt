package com.jp.ac.isc.comasyapp

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ColumnDialogs : DialogFragment(){
    interface Listener {
        //お気に入り
        fun favorite()
        //閉じる
        fun closeD()
    }

    //リスナー用変数
    private var listener : Listener? = null

    //リスナー用変数を設定する処理
    override fun onAttach(context: Context) {
        super.onAttach(context)
        when(context){
            is Listener -> listener= context
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())

        builder.setMessage("コラム内容(test)")

        builder.setPositiveButton("お気に入り"){ dialog, which ->
            listener?.favorite()
        }

        builder.setNegativeButton("閉じる"){ dialog, which ->
            listener?.closeD()
        }
        return builder.create()
    }
}