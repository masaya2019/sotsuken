package com.example.comasyapp

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_take_refrigerator_picture.*
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.IOException

class TakeRefrigeratorPictureActivity : AppCompatActivity() {
    private val cameraRequest = 1888

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_refrigerator_picture)

        if (ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequest)

        // 画像をクリックしたら
        imageView.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequest)
        }

        // 送信ボタンをクリックしたら
        send.setOnClickListener {
            upload()
        }

    }

    var photo: Bitmap? = null

    // 撮った写真をimageViewにセットする
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == cameraRequest && resultCode == Activity.RESULT_OK && data != null) {
            photo = data.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)
        }
    }

    // 写真をアップロードする
    fun upload() {
        Log.i("ok!", "ok！")
        val c = OkHttpClient()

        val stream = ByteArrayOutputStream()
        photo!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", "test.png",
                RequestBody.create(MediaType.parse("image/*png"),byteArray)).build()

        val r = Request.Builder().url("http://10.0.2.2/sotsuken/api/upload_image.php").post(requestBody).build()

        c.newCall(r).enqueue(object: Callback{

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Fail",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("Success","成功？")
                val s = response.body()!!.string()
                Log.e("HI", s)
            }
        })
    }
}