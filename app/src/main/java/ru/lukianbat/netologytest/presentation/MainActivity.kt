package ru.lukianbat.netologytest.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.lukianbat.netologytest.App
import ru.lukianbat.netologytest.navigation.GlobalNavigationGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GlobalNavigationGraph(
                (applicationContext as App).provideAppComponent()
            )
        }
    }
}
