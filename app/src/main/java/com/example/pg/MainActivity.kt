package com.example.pg // Asegúrate de que el paquete sea correcto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Botón 1: Conocimiento y Herramientas (ID: btn_enter)
        val knowledgeButton: Button = findViewById(R.id.btn_enter)
        knowledgeButton.setOnClickListener {
            val intent = Intent(this, KnowledgeActivity::class.java)
            startActivity(intent)
        }

        // Botón 2: Empezar a Soñar (ID: btn_legacy_enter)
        val legacyButton: Button = findViewById(R.id.btn_legacy_enter)
        legacyButton.setOnClickListener {
            val intent = Intent(this, KnowledgeActivity::class.java)
            startActivity(intent)
        }

        // Botón 3: Diario de Sueño (ID: btn_go_tracker)
        val trackerButton: Button = findViewById(R.id.btn_go_tracker)
        trackerButton.setOnClickListener {
            val intent = Intent(this, SleepTrackerActivity::class.java)
            startActivity(intent)
        }

        // ✅ CORRECCIÓN: Botón 4: Referencias Bibliográficas (NUEVO)
        // Este código debe ir aquí, directamente en onCreate.
        val referencesButton: Button = findViewById(R.id.btn_go_references)
        referencesButton.setOnClickListener {
            val intent = Intent(this, ReferencesActivity::class.java)
            startActivity(intent)
        }
    }
}