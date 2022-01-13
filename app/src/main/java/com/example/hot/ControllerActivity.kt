package com.example.hot

import androidx.appcompat.app.AppCompatActivity
import com.example.hot.MainActivity
import com.example.hot.IPicture
import com.example.hot.HotDogClassifier
import android.widget.TextView
import android.graphics.Bitmap
import android.os.Bundle
import com.example.hot.R
import android.content.Intent
import android.provider.MediaStore
import com.example.hot.ControllerActivity
import android.widget.Toast
import android.app.Activity
import android.content.ContentValues
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.hot.ml.Hotdog
import org.tensorflow.lite.support.image.TensorImage
import java.io.IOException
import java.net.URI

class ControllerActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    var mainactivity: MainActivity? = null
    var listener: IPicture.Listener? = null
    private val mImageUri: URI? = null
    private val MODEL_PATH =
        "/c/Users/gould/AndroidStudioProjects/Hot2/SeeFood/app/src/main/ml/hotdog.tflite"
    private val INPUT_SIZE = 512
    private val classifier: HotDogClassifier? = null
    private var result: TextView? = null
    var bitymap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainactivity = MainActivity(this)
        setContentView(mainactivity!!.binding.root)
        imageView = findViewById<View>(R.id.imageView) as ImageView
        val photoButton = findViewById<View>(R.id.button) as Button
        result = findViewById<View>(R.id.result) as TextView
        photoButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, CAMERA_ACTION_CODE)
            } else {
                Toast.makeText(this@ControllerActivity, "Oh Oh spagghettios", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE -> if (resultCode == RESULT_OK) {
                Log.e(ContentValues.TAG, "into line 73")
                val bundle = data!!.extras
                val selectedImage = data.data
                var bitmap: Bitmap
                bitymap = bundle!!["data"] as Bitmap?
                imageView!!.setImageBitmap(bitymap)
            } else if (resultCode == RESULT_CANCELED) {
                Log.e(ContentValues.TAG, "Selecting picture cancelled")
            }
        }
        try {
            val model = Hotdog.newInstance(this.applicationContext)
            // Creates inputs for reference.
            val image = TensorImage.fromBitmap(bitymap)
            // Runs model inference and gets result.
            val outputs: Hotdog.Outputs = model.process(image)
            Log.e(ContentValues.TAG, "Stuck on line 101")
            val probability = outputs.probabilityAsCategoryList
            Log.e(ContentValues.TAG, "Stuck on line 103")
            // Releases model resources if no longer used.
            model.close()
            Log.e(ContentValues.TAG, "Stuck on line 106")
            result!!.text = "The most obvious answer is " + probability[0]
        } catch (e: IOException) {
            // TODO Handle the exception
        }
    }

    companion object {
        private const val CAMERA_ACTION_CODE = 42
        private const val REQUEST_CODE = 42
        private const val MY_CAMERA_PERMISSION_CODE = 42
    }
}