package com.example.pg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent // Necesario para Intents
import android.provider.Settings // Necesario para acceder a los ajustes
import android.widget.Button // Necesario para el botón

class KnowledgeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_knowledge)

        // 1. Encuentra el nuevo botón en la interfaz
        val displaySettingsButton: Button = findViewById(R.id.btn_go_to_display_settings)

        // 2. Programa el click listener
        displaySettingsButton.setOnClickListener {

            // Crea un Intent que le pide al sistema Android abrir la configuración de PANTALLA
            val intent = Intent(Settings.ACTION_DISPLAY_SETTINGS)

            // Intenta iniciar esa Activity del sistema
            try {
                startActivity(intent)
            } catch (e: Exception) {
                // Manejo de error si la configuración no se puede abrir (raro, pero posible)
                // En un proyecto real se usaría un Toast
            }
        }
    }
}