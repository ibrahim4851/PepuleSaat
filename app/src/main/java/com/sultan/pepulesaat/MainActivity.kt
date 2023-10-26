package com.sultan.pepulesaat

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sultan.CheckPermission
import com.sultan.pepulesaat.presentation.navigation.graphs.RootNavigationGraph
import com.sultan.pepulesaat.ui.theme.PepuleSaatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        setContent {
            PepuleSaatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ){
                    CheckPermission()
                    RootNavigationGraph(navController = rememberNavController())
                }
            }
        }
    }
}