package com.soten.tablepay

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import com.google.mlkit.vision.barcode.common.Barcode
import com.soten.tablepay.ui.customer.home.ScanListener
import com.soten.tablepay.ui.view.GraphicOverlay

class BarcodeGraphic(
    private val overlay: GraphicOverlay,
    private val barcode: Barcode,
    private val imageRect: Rect,
    private val scanListener: ScanListener
) : GraphicOverlay.Graphic(overlay) {

    private var rectPaint = Paint().apply {
        color = TEXT_COLOR
        style = Paint.Style.STROKE
        strokeWidth = STROKE_WIDTH
    }

    override fun draw(canvas: Canvas?) {
        barcode.boundingBox?.let { box ->
            // Draws the bounding box around the BarcodeBlock.
            val rect = calculateRect(
                imageRect.height().toFloat(),
                imageRect.width().toFloat(),
                box
            )
            canvas?.drawRoundRect(rect, ROUND_RECT_CORNER, ROUND_RECT_CORNER, rectPaint)

            // Renders the barcode at the bottom of the box.
            barcode.rawValue?.let { value ->
                scanListener.findRestaurant(value)
            }
        }

    }

    companion object {
        private const val TEXT_COLOR = Color.WHITE
        private const val STROKE_WIDTH = 4.0f
        private const val ROUND_RECT_CORNER = 8f
    }

}