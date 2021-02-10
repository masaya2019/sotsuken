package com.jp.ac.isc.comasyapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_take_refrigerator_picture.*
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class TakeRefrigeratorPictureActivity : AppCompatActivity() {
    private val FILE_NAME = "photo.jpg"
    private val REQUEST_CODE = 42
    private lateinit var photoFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_refrigerator_picture)

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photoFile = getPhotoFile(FILE_NAME)

        // This DOESN'T work for API >= 24 (starting 2016)
        // takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoFile)

        val fileProvider = FileProvider.getUriForFile(this, "com.jp.ac.isc.comasyapp.fileprovider", photoFile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        if (takePictureIntent.resolveActivity(this.packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        } else {
            Toast.makeText(this, "Unable to open camera", Toast.LENGTH_SHORT).show()
        }

        // 画像をクリックしたら
        imageView.setOnClickListener {

        }

//        // 送信ボタンをクリックしたら
//        send.setOnClickListener {
//            upload()
//        }

    }

    var takenImage: Bitmap? = null

    private fun getPhotoFile(fileName: String): File {
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val takenImage = data?.extras?.get("data") as Bitmap
            takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            imageView.setImageBitmap(takenImage)
            upload()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }

    // 写真をアップロードする
    private fun upload() {

        val handler = Handler()

        // 本体からrefrigerator_idを取得
        val pref = getSharedPreferences("now_refrigerator_id", Context.MODE_PRIVATE)
        val refrigerator_id = pref.getString("refrigerator_id", "").toString()

        // ランダムな2桁の文字を生成
        val randomNumbr = (0..9).random().toString() + (0..9).random().toString()

        Log.i("ok!", "ok！")
        val c = OkHttpClient()

        val stream = ByteArrayOutputStream()
        takenImage!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("image", "${refrigerator_id}_tmp${randomNumbr}.png",
                RequestBody.create(MediaType.parse("image/*png"),byteArray)).build()

        val r = Request.Builder().url("${GetApiUrl().getApiUrl()}/api/upload_image.php").post(requestBody).build()

        c.newCall(r).enqueue(object: Callback{

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Fail",e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                Log.e("Success","成功？")
                val s = response.body()!!.string()
                Log.e("HI", s)
                handler.post {
                    // Home画面(HomeActivity.kt)へ遷移
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                    startActivity(intent)
                }
            }
        })
    }
}