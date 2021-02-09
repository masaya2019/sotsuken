package com.jp.ac.isc.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class RenameRefrigeratorNameResultDialog: DialogFragment() {
    // 親に渡すためのリスナー定義
    private lateinit var listener: RenameRefrigeratorNameResultDialogListener

    interface RenameRefrigeratorNameResultDialogListener {
        fun onRenameRefrigeratorNameResultDialogPositiveClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as RenameRefrigeratorNameResultDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement RenameRefrigeratorNameResultDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var new_refrigerator_name = ""

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            new_refrigerator_name = bundle.getString("KEY_NEW_NAME").toString()
        }

        val builder = AlertDialog.Builder(context)
        // Dialogの設定
        builder.setTitle("変更後の冷蔵庫名")
        builder.setMessage("\n${new_refrigerator_name}")
        builder.setNegativeButton("O K") { dialog, id ->
            this.listener.onRenameRefrigeratorNameResultDialogPositiveClick(this)
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