package com.soten.tablepay

import android.annotation.SuppressLint
import android.graphics.Rect
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.soten.tablepay.ui.customer.home.ScanListener
import com.soten.tablepay.ui.view.GraphicOverlay
import java.io.IOException

class BarcodeScannerProcessor(
    private val graphicOverlay: GraphicOverlay, private val scanListener: ScanListener,
) :
    ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder().build()
    private val scanner = BarcodeScanning.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        mediaImage?.let {
            detectInImage(InputImage.fromMediaImage(it, imageProxy.imageInfo.rotationDegrees))
                .addOnSuccessListener { results ->
                    onSuccess(
                        results,
                        graphicOverlay,
                        it.cropRect
                    )
                }
                .addOnFailureListener {
                    graphicOverlay.clear()
                    graphicOverlay.postInvalidate()
                    onFailure(it)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }

    private fun detectInImage(image: InputImage): Task<List<Barcode>> {
        return scanner.process(image)
    }

    private fun onSuccess(
        results: List<Barcode>,
        graphicOverlay: GraphicOverlay,
        rect: Rect
    ) {
        graphicOverlay.clear()
        results.forEach {
            val barcodeGraphic = BarcodeGraphic(graphicOverlay, it, rect, scanListener)
            graphicOverlay.add(barcodeGraphic)
        }
        graphicOverlay.postInvalidate()
    }

    private fun onFailure(e: Exception) {
        Log.w(TAG, "Barcode Scan failed.$e")
    }

    fun stop() {
        try {
            scanner.close()
        } catch (e: IOException) {
            Log.e(TAG, "Exception thrown while trying to close Barcode Scanner: $e")
        }
    }

    companion object {
        private const val TAG = "BarcodeScanProcessor"
    }

}