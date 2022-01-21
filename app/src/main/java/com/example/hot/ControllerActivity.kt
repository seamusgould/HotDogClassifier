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


        // use a custom factory so we can pass in the controller upon fragment reconstruction
        // must be called prior to call to super.onCreate()
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

        val context = appContext

        var classifier = HotDogClassifier(appContext, bitymap!!)

        val res = classifier.classify()

        result!!.text = res

    //
//        Check to see which probability comes up.  Depending on the output, make it so that different
//        popups will show up.
//        if (a < b){
//
//        }

    }

    companion object {
        const val REQUEST_CODE = 42
        const val RESULT_OK = 42
    }
}