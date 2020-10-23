package com.example.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class AddGoodsQuantityDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var title = ""

        val inflater = activity!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.add_goods_quantity_dialog, null)!!
        val builder = AlertDialog.Builder(context)

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            title = bundle.getString("KEY_GOODS_NAME").toString()
        }

        // Dialogの設定
        builder.setView(dialogView)
        builder.setTitle("追加する個数を選択してください\n" + "商品名 ： " + title)
        builder.setPositiveButton("追加する") { dialog, id ->
        }
        builder.setNegativeButton("キャンセル") { dialog, id ->
        }

        // NumberPickerの設定
        val np = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
        // NumberPickerの最小値設定
        np.minValue = 1
        // NumberPickerの最大値設定
        np.maxValue = 10
        // NumberPickerの初期値
        np.value = 5

        return builder.create()
    }
}