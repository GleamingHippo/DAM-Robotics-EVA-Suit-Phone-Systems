// 1. MAKE SURE THIS MATCHES YOUR ACTUAL APPLICATION PACKAGE NAME AT THE TOP
package com.example.evahub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.evahub.ui.theme.EVAhubTheme
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview as CameraXPreview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Button
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EVAhubTheme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background // <-- FIXES COLORSCHEME ERROR
                ) {
                    CameraScreen(lifecycleOwner = this@MainActivity) // <-- FIXES GREETING CALL ERROR
                }
            }
        }
    }
}

@Composable
private fun CameraScreen(lifecycleOwner: LifecycleOwner) {
    val context = LocalContext.current
    val permission = Manifest.permission.CAMERA

    var cameraAllowed by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        )
    }

    val requestCamera = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()


    ) { granted ->
        cameraAllowed = granted
    }

    if (!cameraAllowed) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick= {requestCamera.launch(permission) }) {
                Text("Enable camera")
            }
        }
        return
    }

    val previewView = remember(context) { PreviewView(context) }
    var cameraError by remember {mutableStateOf(false) }

    DisposableEffect(previewView, lifecycleOwner) {
        val providerFuture = ProcessCameraProvider.getInstance(context)
        val preview = CameraXPreview.Builder().build()
        var provider: ProcessCameraProvider? = null
        var disposed = false

        providerFuture.addListener({
            if(!disposed) {
                try {
                    val cameraProvider = providerFuture.get()
                    preview.setSurfaceProvider(previewView.surfaceProvider)
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        CameraSelector.DEFAULT_BACK_CAMERA,
                        preview
                    )
                    provider = cameraProvider
                } catch (error: Exception) {
                    cameraError = true
                    Log.e("EVAhubCamera", "Could not start camera preview", error)
                }
            }
        }, ContextCompat.getMainExecutor(context))

        onDispose {
            disposed = true
            provider?.unbind(preview)
        }
    }

    if (cameraError) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Could not start the rear camera.")
        }
    } else {
        AndroidView(
            factory = { previewView },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        color = Color.Cyan
    ) {
        Text(
            text = "Hi $name!",
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EVAhubTheme() {
        Greeting("Colton")
    }
}
