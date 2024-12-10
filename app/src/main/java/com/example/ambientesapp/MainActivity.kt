package com.example.ambientesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backgroundColor = resources.getColor(resources.getIdentifier("main_background", "color", packageName))
        window.decorView.setBackgroundColor(backgroundColor)

        /*
        FirebaseCrashlytics.getInstance().log("Este es un mensaje de prueba (\"Desarrollo de aplicaciones empresariales Android\")")
        FirebaseCrashlytics.getInstance().setCustomKey("app_env", BuildConfig.APP_ENV)
        */

    }
}