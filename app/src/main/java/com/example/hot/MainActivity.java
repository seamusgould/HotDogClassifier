package com.example.hot;

import static java.security.AccessController.getContext;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.hot.databinding.ActivityMainBinding;
import com.example.hot.databinding.MainBinding;
import com.example.hot.IPicture.Listener;

public class MainActivity{
    private static final int CAMERA_REQUEST = 42;
    ControllerActivity activity;
    IPicture.Listener listener;
    ActivityMainBinding binding;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    public MainActivity(ControllerActivity activity) {
        this.binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
        this.activity = activity;
    }

    public void pictureSelected(){

    }
}