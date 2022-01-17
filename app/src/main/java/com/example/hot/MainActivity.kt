package com.example.hot

import com.example.hot.ControllerActivity
import com.example.hot.databinding.ActivityMainBinding

class MainActivity(activity: ControllerActivity) {
    var activity: ControllerActivity
    var listener: IPicture.Listener? = null
    var binding: ActivityMainBinding
    fun pictureSelected() {}

    companion object {
        private const val CAMERA_REQUEST = 42
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    init {
        binding = ActivityMainBinding.inflate(activity.layoutInflater)
        this.activity = activity
    }
}