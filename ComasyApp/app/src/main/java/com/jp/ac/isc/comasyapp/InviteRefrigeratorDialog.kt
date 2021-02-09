package com.jp.ac.isc.comasyapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class InviteRefrigeratorDialog: DialogFragment() {

    // 親に渡すためのリスナー定義
    private lateinit var listener: InviteRefrigeratorDialogListener

    interface InviteRefrigeratorDialogListener {
        fun onInviteRefrigeratorDialogPositiveClick(dialog: DialogFragment, invite_mail_address: String)
        fun onInviteRefrigeratorDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            this.listener = context as InviteRefrigeratorDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(("$context must implement InviteRefrigeratorDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val signinView = inflater.inflate(R.layout.input_mailaddress_dialog, null)

        builder.setView(signinView)
            .setTitle("招待するメールアドレスを入力して下さい")
            .setPositiveButton("招待する") { dialog, id ->
                val invite_mail_address = signinView.findViewById<EditText>(R.id.email).text.toString()
                this.listener.onInviteRefrigeratorDialogPositiveClick(this, invite_mail_address)
            }
            .setNegativeButton("Cancel") { dialog, id ->
                this.listener.onInviteRefrigeratorDialogNegativeClick(this)
            }

        return builder.create()
    }
}