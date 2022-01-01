package com.example.hot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.hot.databinding.ActivityMainBinding;
import com.example.hot.databinding.MainBinding;
import com.example.hot.IPicture.Listener;

public class MainActivity {
    ControllerActivity activity;
    IPicture.Listener listener;
    ActivityMainBinding binding;


    public MainActivity(ControllerActivity activity) {
        this.binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
        this.activity = activity;
    }
}