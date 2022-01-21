package com.example.hot

import android.content.Context
import android.graphics.Bitmap
import com.example.hot.ml.Hotdog
import org.tensorflow.lite.support.image.TensorImage

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
        return prob.toString();
    }
}