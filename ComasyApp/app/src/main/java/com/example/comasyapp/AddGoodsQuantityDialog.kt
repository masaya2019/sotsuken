package com.example.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class AddGoodsQuantityDialog: DialogFragment(), NumberPicker.OnValueChangeListener {

    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeDialogListener
    // 選択したアイテム格納
    private var selectedItem: Int = 0

    interface NoticeDialogListener {
        fun onNumberPickerDialogPositiveClick(dialog: DialogFragment, selectedItem: Int, goods_id: String)
        fun onNumberPickerDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var title = ""
        var goods_id = ""

        val inflater = activity!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.add_goods_quantity_dialog, null)!!
        val builder = AlertDialog.Builder(context)

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            title = bundle.getString("KEY_GOODS_NAME").toString()
            goods_id = bundle.getString("KEY_GOODS_ID").toString()
        }

        // Dialogの設定
        builder.setView(dialogView)
        builder.setTitle("追加する個数を選択してください\n" + "商品名 ： " + title)
        builder.setPositiveButton("追加する") { dialog, id ->
            this.listener.onNumberPickerDialogPositiveClick(this, this.selectedItem, goods_id)
        }
        builder.setNegativeButton("キャンセル") { dialog, id ->
            this.listener.onNumberPickerDialogNegativeClick(this)
        }

        // NumberPickerの設定
        val np = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
        np.setOnValueChangedListener(this)
        // NumberPickerの最小値設定
        np.minValue = 0
        // NumberPickerの最大値設定
        np.maxValue = 10
        // NumberPickerの初期値
        np.value = 0

        return builder.create()
    }

    // 選択された個数に更新
    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        this.selectedItem = newVal
        Log.d("item", this.selectedItem.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}