package com.jp.ac.isc.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SelectChangeOrInviteDialog: DialogFragment() {
    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeSelectChangeOrInviteDialogListener

    interface NoticeSelectChangeOrInviteDialogListener {
        fun onSelectChangeOrInviteDialogClick(dialog: DialogFragment, which: Int)
        fun onSelectChangeOrInviteDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeSelectChangeOrInviteDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeSelectChangeOrInviteDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val strList = arrayOf("冷蔵庫を切り替える", "新しい冷蔵庫を追加", "冷蔵庫の名前を変更", "招待する")

        val builder = AlertDialog.Builder(context)
        // Dialogの設定
        builder.setTitle("何 を し ま す か ？")
        builder.setItems(strList) { dialog, which ->
            this.listener.onSelectChangeOrInviteDialogClick(this, which)
        }
        builder.setNegativeButton("キャンセル") { dialog, id ->
            this.listener.onSelectChangeOrInviteDialogNegativeClick(this)
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