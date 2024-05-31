package com.airness.myapplication.util

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

class TopRoundedCornersTransformation(private val radius: Float) : BitmapTransformation() {

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(("TopRoundedCornersTransformation$radius").toByteArray())
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val transformed = Bitmap.createBitmap(toTransform, 0, 0, toTransform.width, toTransform.height)
        return cropAndRoundCorners(pool, transformed, radius)
    }

    private fun cropAndRoundCorners(pool: BitmapPool, source: Bitmap, radius: Float): Bitmap {
        val width = source.width
        val height = source.height

        val output = pool.get(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint().apply {
            isAntiAlias = true
            shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        }

        val path = Path().apply {
            addRoundRect(
                RectF(0f, 0f, width.toFloat(), height.toFloat()),
                floatArrayOf(radius, radius, radius, radius, 0f, 0f, 0f, 0f),
                Path.Direction.CW
            )
        }

        canvas.drawPath(path, paint)
        return output
    }
}
