package com.example.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ChangeGoodsQuantityResultDialog: DialogFragment() {

    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeChangeGoodsResultDialogListener

    interface NoticeChangeGoodsResultDialogListener {
        fun onNoticeChangeGoodsResultPositiveClick(dialog: DialogFragment, search_data: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeChangeGoodsResultDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeChangeGoodsDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var goods_name = ""
        var change_quantity = ""
        var search_data = ""

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            goods_name = bundle.getString("KEY_GOODS_NAME").toString()
            change_quantity = bundle.getString("KEY_CHANGE_QUANTITY").toString()
            search_data = bundle.getString("KEY_SEARCH_DATA").toString()
        }

        if (change_quantity.toInt() > 0) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("変更完了のお知らせ")
                .setMessage("\n${goods_name}を${change_quantity}個増やしました")
                .setPositiveButton("確　認") { dialog, id ->
                    this.listener.onNoticeChangeGoodsResultPositiveClick(this, search_data)
                }
            return builder.create()
        } else {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("変更完了のお知らせ")
                .setMessage("\n${goods_name}を${0 - change_quantity.toInt()}個減らしました")
                .setPositiveButton("確　認") { dialog, id ->
                    this.listener.onNoticeChangeGoodsResultPositiveClick(this, search_data)
                }
            return builder.create()
        }
    }
}