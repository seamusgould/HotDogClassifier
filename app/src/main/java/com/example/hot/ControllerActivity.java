package com.example.hot;

import static android.content.ContentValues.TAG;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hot.ml.Hotdog;

import org.checkerframework.checker.units.qual.C;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.text.BreakIterator;
import java.util.List;

public class ControllerActivity extends AppCompatActivity{
    private static final int CAMERA_ACTION_CODE = 42;
    private static final int REQUEST_CODE = 42;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 42;
    public MainActivity mainactivity;
    public IPicture.Listener listener;
    private URI mImageUri;
    private String MODEL_PATH = "/c/Users/gould/AndroidStudioProjects/Hot2/SeeFood/app/src/main/ml/hotdog.tflite";
    private int INPUT_SIZE = 512;
    private HotDogClassifier classifier;
    private TextView result;
    Bitmap bitymap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mainactivity = new MainActivity(this);
        setContentView(mainactivity.binding.getRoot());
        this.imageView = (ImageView) this.findViewById(R.id.imageView);
        Button photoButton = (Button) this.findViewById(R.id.button);
        result = (TextView) this.findViewById(R.id.result);

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, CAMERA_ACTION_CODE);
                }
                else {
                    Toast.makeText(ControllerActivity.this, "Oh Oh spagghettios", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            switch (requestCode) {
                case REQUEST_CODE:
                    if (resultCode == ControllerActivity.RESULT_OK) {
                        Log.e(TAG, "into line 73");
                        Bundle bundle = data.getExtras();
                        Uri selectedImage = data.getData();
                        Bitmap bitmap;
                        bitymap = (Bitmap) bundle.get("data");
                        imageView.setImageBitmap(bitymap);
                    }
            else if (resultCode == ControllerActivity.RESULT_CANCELED) {
                Log.e(TAG, "Selecting picture cancelled");
            }
            break;
        }
        try {
            Hotdog model = Hotdog.newInstance(this.getApplicationContext());
            // Creates inputs for reference.
            TensorImage image = TensorImage.fromBitmap(bitymap);
            // Runs model inference and gets result.
            Hotdog.Outputs outputs = model.process(image);
            Log.e(TAG, "Stuck on line 101");
            List<Category> probability = outputs.getProbabilityAsCategoryList();
            Log.e(TAG, "Stuck on line 103");
            // Releases model resources if no longer used.
            model.close();
            Log.e(TAG, "Stuck on line 106");
            result.setText("The most obvious answer is " + probability.get(0));
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }
}
