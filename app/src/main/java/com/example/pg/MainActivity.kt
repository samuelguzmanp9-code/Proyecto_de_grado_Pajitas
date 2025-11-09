package com.example.pg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import com.google.android.material.floatingactionbutton.FloatingActionButton // ✅ Importación necesaria para el FAB

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


        // Botón 3: Diario de Sueño (ID: btn_go_tracker)
        val trackerButton: Button = findViewById(R.id.btn_go_tracker)
        trackerButton.setOnClickListener {
            val intent = Intent(this, SleepTrackerActivity::class.java)
            startActivity(intent)
        }

        // ✅ CORRECCIÓN FINAL para el FAB
        // Cambiamos el tipo de variable de Button a FloatingActionButton
        val referencesButton: FloatingActionButton = findViewById(R.id.btn_go_references)
        referencesButton.setOnClickListener {
            val intent = Intent(this, ReferencesActivity::class.java)
            startActivity(intent)
        }
        // ❌ Eliminamos la línea duplicada y mal colocada que estaba aquí
    }
}