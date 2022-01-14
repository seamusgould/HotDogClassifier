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
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.hot.ml.Hotdog
import org.tensorflow.lite.support.image.TensorImage

class ControllerActivity : AppCompatActivity() {

    var imageView: ImageView? = null
    var mainactivity: MainView? = null
    var result: TextView? = null
    var bitymap: Bitmap? = null
    var appContext: Context? = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainactivity = MainView(this)
        setContentView(mainactivity!!.binding.root)

    /*        photoButton.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, Companion.CAMERA_ACTION_CODE)
            } else {
                Toast.makeText(this@ControllerActivity, "Oh Oh spagghettios", Toast.LENGTH_LONG)
                    .show()
            }
        }*/
    }

    fun requestPhoto() {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, 42)
            } else {
                Toast.makeText(this, "Oh Oh spagghettios", Toast.LENGTH_LONG).show()
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
    }
}