package com.example.hot;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.Interpreter;
import org.tensorflow.lite.TensorFlowLite;
import org.tensorflow.lite.support.common.FileUtil;
import org.tensorflow.lite.support.image.TensorImage;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class HotDogClassifier {
    private String MODEL_PATH = "/c/Users/gould/AndroidStudioProjects/Hot2/SeeFood/app/src/main/ml/hotdog.tflite";
    MappedByteBuffer tfliteModel;
    Activity activity;

    public HotDogClassifier(MainActivity activity, AssetManager assetManager, String modelPath, int inputSize) throws IOException {}

    public String recognizeHotDog(Bitmap bitmap) throws IOException {
        Interpreter.Options tfliteOptions = (new Interpreter.Options());
        tfliteModel = FileUtil.loadMappedFile(activity.getApplicationContext(), MODEL_PATH);

// create tflite interpreter
        Interpreter tfliteInterpreter = new Interpreter(tfliteModel, tfliteOptions);

/*// get model parameters (index tensor is 0 for a single image)
        DataType myImageDataType = tfliteInterpreter.getInputTensor(0).dataType();
        myTensorImage = new TensorImage(myImageDataType);

// load bitmap
        myTensorImage.load(myImage);

// run inference
        tfliteInterpreter.run(myTensorImage.getBuffer(), output);*/
    return "yas";
    }
}
