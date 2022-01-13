package com.example.hot

import com.example.hot.MainActivity
import android.content.res.AssetManager
import android.app.Activity
import kotlin.Throws
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import java.io.IOException
import java.nio.MappedByteBuffer

class HotDogClassifier(
    activity: MainActivity?,
    assetManager: AssetManager?,
    modelPath: String?,
    inputSize: Int
) {
    private val MODEL_PATH =
        "/c/Users/gould/AndroidStudioProjects/Hot2/SeeFood/app/src/main/ml/hotdog.tflite"
    var tfliteModel: MappedByteBuffer? = null
    var activity: Activity? = null
    @Throws(IOException::class)
    fun recognizeHotDog(bitmap: Bitmap?): String {
        val tfliteOptions = Interpreter.Options()
        tfliteModel = FileUtil.loadMappedFile(activity!!.applicationContext, MODEL_PATH)

// create tflite interpreter
        val tfliteInterpreter = Interpreter(tfliteModel, tfliteOptions)

/*// get model parameters (index tensor is 0 for a single image)
        DataType myImageDataType = tfliteInterpreter.getInputTensor(0).dataType();
        myTensorImage = new TensorImage(myImageDataType);

// load bitmap
        myTensorImage.load(myImage);

// run inference
        tfliteInterpreter.run(myTensorImage.getBuffer(), output);*/return "yas"
    }
}