package com.example.hot

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.graphics.Bitmap
import android.os.Bundle
import android.content.Intent
import android.provider.MediaStore
import android.widget.Toast
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.hot.databinding.ActivityMainBinding
import com.example.hot.ml.Hotdog
import org.tensorflow.lite.support.image.TensorImage

abstract class ControllerActivity : AppCompatActivity() {

    var bundle: Bundle? = null
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
        this.imageView = amb?.imageView as ImageView
        this.photoButton = amb?.button as Button
        this.result = amb?.result as TextView

        photoButton?.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            var packageManager: PackageManager = appContext!!.getPackageManager()

            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 42)
            }

            onActivityResult(42, 42, intent)
            Log.d("MainView", "line 50")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Companion.REQUEST_CODE -> if (resultCode == RESULT_OK) {
                val bundle = data!!.extras
                bitymap = bundle!!["data"] as Bitmap?
                imageView!!.setImageBitmap(bitymap)
            } else if (resultCode == RESULT_CANCELED) {
                Log.e(ContentValues.TAG, "Selecting picture cancelled")
            }
        }

        val context = appContext
        val model = Hotdog.newInstance(context as Context)
        // Creates inputs for reference.
        val image = TensorImage.fromBitmap(bitymap)
        // Runs model inference and gets result.
        val outputs = model.process(image)
        val probability = outputs.probabilityAsCategoryList
        result!!.text = probability.elementAt(0).toString()
        // Releases model resources if no longer used.
        model.close()
    }
    companion object {
        const val CAMERA_ACTION_CODE = 42
        const val REQUEST_CODE = 42
        const val RESULT_OK = 42
    }
}