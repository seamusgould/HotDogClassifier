package com.example.hot

import android.content.Context
import android.graphics.Bitmap
import com.example.hot.ml.Hotdog
import com.example.hot.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class HotDogClassifier(context: Context?, bitmap:Bitmap) {

    var bitmap: Bitmap? = null
    var context: Context? = null

    init{
        this.context = context
        this.bitmap = bitmap
    }

    fun classify(): String? {

        val model = Hotdog.newInstance(context as Context)
        // Creates inputs for reference.
        val image = TensorImage.fromBitmap(bitmap)
        // Runs model inference and gets result.
        val outputs = model.process(image)
        val probability = outputs.probabilityAsCategoryList
        // Releases model resources if no longer used.
        model.close()
        val prob = probability.elementAt(0).score
//        var r = probability.contains("hot_dog")
        return prob.toString()
    }

    fun iclassify(): String? {

        // https://www.youtube.com/watch?v=RJjiCwKAR8w - watched it

        val model = Model.newInstance(context as Context)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 128, 128, 3), DataType.FLOAT32)

        var byteBuffer : ByteBuffer = ByteBuffer.allocateDirect(4*1)

        inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

        model.close()
        val prob = outputFeature0[0].toString()
//        var r = probability.contains("hot_dog")
        return prob
    }
}