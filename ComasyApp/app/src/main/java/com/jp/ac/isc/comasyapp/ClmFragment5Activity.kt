package com.jp.ac.isc.comasyapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ClmFragment5Activity : Fragment() {
    //フラグメント生成
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //ここのコラムナンバーを変更する
        return inflater.inflate(R.layout.activity_clm5,container,false)
    }

    //戻るボタン
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonprev = view.findViewById<Button>(R.id.returnColumnButton)
        buttonprev.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

}
