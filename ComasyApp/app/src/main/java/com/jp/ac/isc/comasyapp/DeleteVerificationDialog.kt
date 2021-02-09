package com.jp.ac.isc.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class DeleteVerificationDialog: DialogFragment() {

    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeDeleteVerificationDialogListener

    interface NoticeDeleteVerificationDialogListener {
        fun onDeleteVerificationDialogPositiveClick(dialog: DialogFragment, picture_name: String, next_picture_name: String)
        fun onDeleteVerificationDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeDeleteVerificationDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeDeleteVerificationDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var picture_name = ""
        var next_picture_name = ""

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            picture_name = bundle.getString("KEY_PICTURE_NAME").toString()
            next_picture_name = bundle.getString("KEY_NEXT_PICTURE_NAME").toString()
        }

        val builder = AlertDialog.Builder(context)
        // Dialogの設定
        builder.setTitle("この写真を削除しますか？")
        builder.setPositiveButton("削除する") { dialog, id ->
            this.listener.onDeleteVerificationDialogPositiveClick(this, picture_name, next_picture_name)
        }
        builder.setNegativeButton("キャンセル") { dialog, id ->
            this.listener.onDeleteVerificationDialogNegativeClick(this)
        }

        return builder.create()
    }

}