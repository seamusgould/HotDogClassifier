package com.example.hot;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

import com.example.hot.databinding.ActivityMainBinding;
import com.example.hot.IPicture.Listener;

public class MainActivity extends AppCompatActivity implements IPicture.Listener {
    ActivityMainBinding binding;
    IPicture.Listener listener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = ActivityMainBinding.inflate(inflater);
        return binding.getRoot();
    }

    public void clickPictureSelected(){
        this.binding.button.setOnClickListener((clickedView) -> {
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(this.binding.fragmentContainerView.getId(), fragment)
                    .commitNow();
           }
    }
}