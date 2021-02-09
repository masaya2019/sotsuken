package com.jp.ac.isc.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class RenameRefrigeratorNameDialog: DialogFragment() {
    // 親に渡すためのリスナー定義
    private lateinit var listener: RenameRefrigeratorNameDialogListener

    interface RenameRefrigeratorNameDialogListener {
        fun onRenameRefrigeratorNameDialogPositiveClick(dialog: DialogFragment, new_refrigerator_name: String)
        fun onRenameRefrigeratorNameDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as RenameRefrigeratorNameDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement RenameRefrigeratorNameDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val signinView = inflater.inflate(R.layout.input_refrigerator_dialog, null)

        builder.setView(signinView)
            .setTitle("新しい冷蔵庫名を入力して下さい")
            .setPositiveButton("変更する") { dialog, id ->
                val new_refrigerator_name = signinView.findViewById<EditText>(R.id.new_refrigerator_name).text.toString()
                this.listener.onRenameRefrigeratorNameDialogPositiveClick(this, new_refrigerator_name)
            }
            .setNegativeButton("Cancel") { dialog, id ->
                this.listener.onRenameRefrigeratorNameDialogNegativeClick(this)
            }

        return builder.create()
    }
}