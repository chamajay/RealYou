/*
 * RealYou
 * Copyright (C) 2025 Chamath Jayasena
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.chamathjayasena.realyou.utils

import android.content.Context
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner

/**
 * Initializes the camera.
 *
 * @param context the application context.
 * @param lifecycleOwner the lifecycle owner.
 * @param realPreviewView the real preview view.
 * @param mirroredPreviewView optional mirrored preview view.
 */
fun initializeCamera(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    realPreviewView: PreviewView,
    mirroredPreviewView: PreviewView? = null
) {
    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    cameraProviderFuture.addListener({
        // Used to bind the lifecycle of cameras to the lifecycle owner
        val cameraProvider: ProcessCameraProvider
        try {
            cameraProvider = cameraProviderFuture.get()
        } catch (e: Exception) {
            Toast.makeText(context, "Camera initialization failed.", Toast.LENGTH_SHORT).show()
            throw e
        }

        // Check if the front camera is available
        if (!isFrontCameraAvailable(cameraProvider)) {
            Toast.makeText(
                context,
                "Front camera initialization failed.",
                Toast.LENGTH_SHORT
            ).show()
            throw IllegalStateException("Front camera is not available.")
        }

        // Use cases
        // Real preview
        val realPreview = createPreview(realPreviewView)
        val useCases = mutableListOf(realPreview)
        // Mirrored preview
        mirroredPreviewView?.let {
            val mirroredPreview = createPreview(it)
            useCases.add(mirroredPreview)
        }

        val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

        try {
            // Unbind use cases before rebinding
            cameraProvider.unbindAll()

            // Bind use cases to camera
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                *useCases.toTypedArray()
            )
        } catch (e: Exception) {
            Toast.makeText(context, "Camera initialization failed.", Toast.LENGTH_SHORT).show()
            throw e
        }
    }, ContextCompat.getMainExecutor(context))
}

/**
 * Checks if the front camera is available on the device.
 *
 * @param cameraProvider the camera provider to check for available cameras.
 * @return `true` if the front camera is available, `false` otherwise.
 */
private fun isFrontCameraAvailable(cameraProvider: ProcessCameraProvider): Boolean {
    return cameraProvider.availableCameraInfos.any {
        it.lensFacing == CameraSelector.LENS_FACING_FRONT
    }
}

/**
 * Creates a camera preview using a [PreviewView].
 *
 * @param previewView the [PreviewView] to display the camera preview.
 * @return the created [Preview].
 */
private fun createPreview(previewView: PreviewView): Preview {
    return Preview.Builder()
        .build()
        .also {
            it.surfaceProvider = previewView.surfaceProvider
        }
}
