package com.example.hot

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.graphics.Bitmap
import android.os.Bundle
import android.content.Intent
import android.provider.MediaStore
import android.content.Context
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import com.example.hot.databinding.ActivityMainBinding
import com.example.hot.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import android.os.SystemClock
import java.io.ByteArrayOutputStream


class ControllerActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null
    var imageView: ImageView? = null
    var result: TextView? = null
    var bitymap: Bitmap? = null
    var appContext: Context? = this
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
        binding?.nothotdog?.visibility = View.INVISIBLE
        binding?.hotdog?.visibility = View.INVISIBLE
        binding?.possible?.visibility = View.INVISIBLE

        super.onActivityResult(requestCode, resultCode, data)
        this.bitymap = data?.extras?.get("data") as Bitmap
        imageView!!.setImageBitmap(bitymap)
        Log.d("ControllerActivity", "Line 61")

        var model = Model.newInstance(appContext!!)

        Log.d("ControllerActivity", "Line 61")


        var classifier = HotDogClassifier(appContext, bitymap!!)

        var res = classifier.classify() //this is a string?

        val outputFeature0 = res?.toDouble()

        if (outputFeature0!! > .8){
            displayHotDog()
        }
        if (outputFeature0!! <= .8 && outputFeature0 >= .3){
            displayUncertain()
        }

        if (outputFeature0!! < .3){
            displayNotHogDog()
        }

        binding?.result?.visibility = View.VISIBLE

        binding?.imageView?.visibility = View.VISIBLE

    }

    private fun displayNotHogDog() {
        binding?.nothotdog?.visibility = View.VISIBLE
    }

    private fun displayUncertain() {
        binding?.possible?.visibility = View.VISIBLE
    }

    private fun displayHotDog() {
        binding?.hotdog?.visibility = View.VISIBLE
    }

    companion object {
        const val REQUEST_CODE = 42
    }
}