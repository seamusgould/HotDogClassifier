package com.example.hot

import android.content.Intent
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.hot.ControllerActivity
import com.example.hot.IPicture
import com.example.hot.databinding.ActivityMainBinding

class MainView(activity: ControllerActivity) {
    var activity: ControllerActivity
    var listener: IPicture.Listener? = null
    var binding: ActivityMainBinding
    var imageView: ImageView? = null
    var photoButton: Button? = null
    var result: TextView? = null

    companion object {
        private const val CAMERA_REQUEST = 42
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    init {
        binding = ActivityMainBinding.inflate(activity.layoutInflater)
        this.activity = activity
        imageView = binding.imageView as ImageView
        photoButton = binding.button as Button
        result = binding.result as TextView
    }

    fun takePhoto() {
        photoButton?.setOnClickListener {
            Toast.makeText(activity, "Oh Oh spagghettios", Toast.LENGTH_LONG).show()
            activity.requestPhoto()
        }
    }
}