package com.example.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class NoTextErrorDialog: DialogFragment() {
    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeNoTextErrorDialogListener

    interface NoticeNoTextErrorDialogListener {
        fun onNoTextErrorDialogPositiveClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeNoTextErrorDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeNoTextErrorDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(context)
        // Dialogの設定
        builder.setTitle("文字を入力してください！")
        builder.setPositiveButton("確認") { dialog, id ->
            this.listener.onNoTextErrorDialogPositiveClick(this)
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