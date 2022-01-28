package com.example.hot

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.graphics.Bitmap
import android.os.Bundle
import android.content.Intent
import android.provider.MediaStore
import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.hot.databinding.ActivityMainBinding

class ControllerActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var imageView: ImageView? = null
    var result: TextView? = null
    var bitymap: Bitmap? = null
    var appContext: Context? = this
    val amb: ActivityMainBinding? = null
    var photoButton: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        this.imageView = binding?.imageView as ImageView
        this.photoButton = binding?.button as Button
        this.result = binding?.result as TextView

        photoButton?.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            var packageManager: PackageManager = appContext!!.getPackageManager()

            if (intent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(intent, REQUEST_CODE)
            }
            Log.d("MainView", "line 50")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        this.bitymap = data?.extras?.get("data") as Bitmap
        imageView!!.setImageBitmap(bitymap)
        Log.d("ControllerActivity", "Line 61")

        var classifier = HotDogClassifier(appContext, bitymap!!)

        var res = classifier.classify()// as Int

//        if (res >= .95){
//            displayHotDog()
//        }
//        if (res <= .95 && res >= .05){
//            displayUncertain();
//        }
//
//        if (res <= .05){
//            displayNotHogDog()
//        }

        result!!.text = res.toString()


    }

    private fun displayNotHogDog() {
        TODO("Not yet implemented")
    }

    private fun displayUncertain(): Any {
        TODO("Not yet implemented")
    }

    private fun displayHotDog() {
        TODO("Not yet implemented")
    }

    companion object {
        const val REQUEST_CODE = 42
    }
}