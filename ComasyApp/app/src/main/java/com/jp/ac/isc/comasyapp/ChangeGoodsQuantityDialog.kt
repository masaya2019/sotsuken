package com.jp.ac.isc.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment

class ChangeGoodsQuantityDialog: DialogFragment(), NumberPicker.OnValueChangeListener {
    // 親に渡すためのリスナー定義
    private lateinit var listener: NoticeChangeGoodsDialogListener
    // 選択したアイテム格納
    private var selectedItem: Int = 0

    interface NoticeChangeGoodsDialogListener {
        fun onNumberPickerDialogAddClick(dialog: DialogFragment, selectedItem: Int, goods_id: String, goods_name: String, search_data: String)
        fun onNumberPickerDialogSubClick(dialog: DialogFragment, selectedItem: Int, goods_id: String, goods_name: String, search_data: String)
        fun onNumberPickerDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as NoticeChangeGoodsDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement NoticeChangeGoodsDialogListener"))
        }
    }

    // ダイアログ作成
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        var goods_name = ""
        var goods_id = ""
        var goods_quantity = ""
        var search_data = ""

        val inflater = activity!!.layoutInflater
        val dialogView = inflater.inflate(R.layout.add_goods_quantity_dialog, null)!!
        val builder = AlertDialog.Builder(context)

        // Bundleを取得する
        val bundle = arguments
        // Bundleがセットされていたら値を受け取る
        if (bundle != null) {
            goods_name = bundle.getString("KEY_GOODS_NAME").toString()
            goods_id = bundle.getString("KEY_GOODS_ID").toString()
            goods_quantity = bundle.getString("KEY_GOODS_QUANTITY").toString()
            search_data = bundle.getString("KEY_SEARCH_DATA").toString()
        }

        // Dialogの設定
        builder.setView(dialogView)
        builder.setTitle("変更する個数を選択してください\n" + "商品名 ： " + goods_name)
        builder.setPositiveButton("増 や す") { dialog, id ->
            this.listener.onNumberPickerDialogAddClick(this, this.selectedItem, goods_id, goods_name, search_data)
        }
        builder.setNegativeButton("減 ら す") { dialog, id ->
            this.listener.onNumberPickerDialogSubClick(this, this.selectedItem, goods_id, goods_name, search_data)
        }
        builder.setNeutralButton("キャンセル") { dialog, id ->
            this.listener.onNumberPickerDialogNegativeClick(this)
        }

        // NumberPickerの設定
        val np = dialogView.findViewById<NumberPicker>(R.id.numberPicker)
        np.setOnValueChangedListener(this)
        // NumberPickerの最小値設定
        np.minValue = 0
        // NumberPickerの最大値設定
        np.maxValue = goods_quantity.toInt()
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