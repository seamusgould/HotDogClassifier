package com.example.hot;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.View;

public class ControllerActivity extends AppCompatActivity implements IPicture.Listener{
    public MainActivity mainactivity;
    public IPicture.Listener listener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainactivity = new MainActivity(this);
        setContentView(mainactivity.binding.getRoot());
    }

    @Override
    public void clickPictureSelected() {

    }
}
