package com.soten.tablepay.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.ScaleGestureDetector
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.soten.tablepay.ui.view.GraphicOverlay
import com.soten.tablepay.BarcodeScannerProcessor
import com.soten.tablepay.ui.customer.home.ScanListener
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraXManager(
    private val context: Context,
    private val finderView: PreviewView,
    private val lifecycleOwner: LifecycleOwner,
    private val graphicOverlay: GraphicOverlay,
    private val scanListener: ScanListener
) {

    private var preview: Preview? = null
    private var camera: Camera? = null
    private var cameraProvider: ProcessCameraProvider? = null
    private var imageAnalyzer: ImageAnalysis? = null

    private lateinit var cameraExecutor: ExecutorService

    var cameraSelectorOption = CameraSelector.LENS_FACING_BACK

    init {
        createNewExecutor()
    }

    private fun createNewExecutor() {
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener(
            {
                cameraProvider = cameraProviderFuture.get()
                preview = Preview.Builder().build()

                imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also { it.setAnalyzer(cameraExecutor, selectAnalyzer()) }

                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(cameraSelectorOption)
                    .build()

                setUpPinchToZoom()
                setCameraConfig(cameraProvider, cameraSelector)

            }, ContextCompat.getMainExecutor(context)
        )
    }

    private fun selectAnalyzer(): ImageAnalysis.Analyzer {
        return BarcodeScannerProcessor(graphicOverlay, scanListener)
    }

    private fun setCameraConfig(
        cameraProvider: ProcessCameraProvider?,
        cameraSelector: CameraSelector,
    ) {
        try {
            cameraProvider?.unbindAll()

            camera = cameraProvider?.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageAnalyzer
            )
            preview?.setSurfaceProvider(
                finderView.surfaceProvider
            )
        } catch (e: Exception) {
            Log.e(TAG, "카메라 연결 실패", e)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpPinchToZoom() {
        val listener = object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val currentZoomRatio = camera?.cameraInfo?.zoomState?.value?.zoomRatio ?: 1F
                val delta = detector.scaleFactor
                camera?.cameraControl?.setZoomRatio(currentZoomRatio * delta)
                return true
            }
        }
        val scaleGestureDetector = ScaleGestureDetector(context, listener)
        finderView.setOnTouchListener { _, event ->
            finderView.post {
                scaleGestureDetector.onTouchEvent(event)
            }
            return@setOnTouchListener true
        }
    }

    fun cameraShutDown() {
        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraXManager"
    }

}