package com.example.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class NoOtherRefrigeratorDialog: DialogFragment() {

    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeNoOtherRefrigeratorDialogListener

    interface NoticeNoOtherRefrigeratorDialogListener {
        fun onNoOtherRefrigeratorDialogPositiveClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeNoOtherRefrigeratorDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeNoOtherRefrigeratorDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(context)
        // Dialogの設定
        builder.setTitle("切り替え可能な冷蔵庫がありません")
        builder.setPositiveButton("確認") { dialog, id ->
            this.listener.onNoOtherRefrigeratorDialogPositiveClick(this)
        }
        return builder.create()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}