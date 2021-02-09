package com.jp.ac.isc.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SelectNewRefrigeratorDialog: DialogFragment() {

    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeSelectNewRefrigeratorDialogListener

    interface NoticeSelectNewRefrigeratorDialogListener {
        fun onSelectNewRefrigeratorDialogClick(dialog: DialogFragment, new_refrigerator_id: String)
        fun onSelectNewRefrigeratorDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeSelectNewRefrigeratorDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeSelectNewRefrigeratorDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(context)

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {

            val strList = bundle.getStringArray("KEY_DATA_ARRAY")

            // Dialogの設定
            builder.setTitle("どの冷蔵庫に切り替えますか？")
            builder.setItems(strList) { dialog, which ->
                this.listener.onSelectNewRefrigeratorDialogClick(this, strList?.get(which).toString().substring(strList?.get(which).toString().length - 6, strList?.get(which).toString().length - 1))
            }
            builder.setNegativeButton("キャンセル") { dialog, id ->
                this.listener.onSelectNewRefrigeratorDialogNegativeClick(this)
            }
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