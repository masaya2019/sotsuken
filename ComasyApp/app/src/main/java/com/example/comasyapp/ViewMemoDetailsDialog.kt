package com.example.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ViewMemoDetailsDialog: DialogFragment() {

    // 親に渡すためのリスナー定義
    private lateinit var listener: ViewMemoDetailsDialogListener

    interface ViewMemoDetailsDialogListener {
        fun onViewMemoDetailsDialogPositiveClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as ViewMemoDetailsDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement ViewMemoDetailsDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var memo_title = ""
        var memo_contents = ""

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            memo_title = bundle.getString("KEY_MEMO_TITLE").toString()
            memo_contents = bundle.getString("KEY_MEMO_CONTENTS").toString()
        }

        val builder = AlertDialog.Builder(context)
        // Dialogの設定
        builder.setTitle("タイトル：${memo_title}")
        builder.setMessage("\n${memo_contents}")
        builder.setNegativeButton("O K") { dialog, id ->
            this.listener.onViewMemoDetailsDialogPositiveClick(this)
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