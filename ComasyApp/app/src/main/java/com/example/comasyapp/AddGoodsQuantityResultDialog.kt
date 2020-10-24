package com.example.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AddGoodsQuantityResultDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var goods_name = ""
        var add_quantity = ""

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            goods_name = bundle.getString("KEY_GOODS_NAME").toString()
            add_quantity = bundle.getString("KEY_ADD_QUANTITY").toString()
        }

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("追　加　完　了　の　お　知　ら　せ")
            .setMessage("\n${goods_name}を${add_quantity}個追加しました")
            .setPositiveButton("確　認") { dialog, id ->
            }
        return builder.create()
    }
}