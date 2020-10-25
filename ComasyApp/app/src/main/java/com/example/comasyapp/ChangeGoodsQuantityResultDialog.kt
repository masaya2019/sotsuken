package com.example.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ChangeGoodsQuantityResultDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var goods_name = ""
        var change_quantity = ""

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            goods_name = bundle.getString("KEY_GOODS_NAME").toString()
            change_quantity = bundle.getString("KEY_ADD_QUANTITY").toString()
        }

        if (change_quantity.toInt() > 0) {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("変　更　完　了　の　お　知　ら　せ")
                .setMessage("\n${goods_name}を${change_quantity}個増やしました")
                .setPositiveButton("確　認") { dialog, id ->
                }
            return builder.create()
        } else {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("変 更 完　了　の　お　知　ら　せ")
                .setMessage("\n${goods_name}を${0 - change_quantity.toInt()}個減らしました")
                .setPositiveButton("確　認") { dialog, id ->
                }
            return builder.create()
        }
    }
}