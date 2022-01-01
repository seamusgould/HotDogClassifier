package com.example.hot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hot.databinding.ActivityMainBinding;


public class HomeFragment extends Fragment {
    IPicture.Listener listener;
    ActivityMainBinding binding;

    public HomeFragment(IPicture.Listener listener) {
        this.listener = listener;
    }

    public View onCreateView(LayoutInflater inflater) {
        this.binding = ActivityMainBinding.inflate(inflater);
        return this.binding.getRoot();
    }
}
