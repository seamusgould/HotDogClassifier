package com.example.hot

import android.app.Activity.RESULT_CANCELED
import android.app.PendingIntent.getActivity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.hot.ControllerActivity
import com.example.hot.IPicture
import com.example.hot.databinding.ActivityMainBinding
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import android.content.Intent.getIntent





class MainView(activity: ControllerActivity) {
    var activity: ControllerActivity
    var binding: ActivityMainBinding

    init {

        binding = ActivityMainBinding.inflate(activity.layoutInflater)
        this.activity = activity
    }

     companion object {
        const val CAMERA_REQUEST = 42
        const val REQUEST_IMAGE_CAPTURE = 1
    }
}