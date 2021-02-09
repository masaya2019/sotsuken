package com.jp.ac.isc.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SelectNextActionDialog: DialogFragment() {

    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeNextActionDialogListener

    interface NoticeNextActionDialogListener {
        fun onSelectNextActionDialogClick(dialog: DialogFragment, which: Int)
        fun onSelectNextActionDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeNextActionDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeNextActionDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val strList = arrayOf("冷蔵庫の写真を登録", "冷蔵庫の中身を登録（写真を読み込む）", "冷蔵庫の中身を登録（一覧から選択）")

        val builder = AlertDialog.Builder(context)
        // Dialogの設定
        builder.setTitle("何 を し ま す か ？")
        builder.setItems(strList) { dialog, which ->
            this.listener.onSelectNextActionDialogClick(this, which)
        }
        builder.setNegativeButton("キャンセル") { dialog, id ->
            this.listener.onSelectNextActionDialogNegativeClick(this)
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