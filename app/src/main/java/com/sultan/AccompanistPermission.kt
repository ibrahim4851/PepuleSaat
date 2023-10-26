package com.sultan

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun CheckPermission() {
    val context = LocalContext.current
    val launcher: ActivityResultLauncher<String> = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
        } else {
            Toast.makeText(context, "Bildirim Ayarlarından İzin Verebilirsiniz", Toast.LENGTH_LONG)
        }
    }


    LaunchedEffect(launcher) {
        launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
    }
}

