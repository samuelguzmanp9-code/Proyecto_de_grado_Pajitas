package com.example.pg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class SleepHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_history)

        val historyListView: ListView = findViewById(R.id.lv_sleep_entries)

        // 1. Preparar la data para el ListView
        val entryStrings = SleepData.sleepEntries.map { entry ->
            // Mapea cada objeto SleepEntry a una cadena de texto para mostrar
            "${entry.date} | ${entry.bedtime} - ${entry.wakeupTime} | Calidad: ${entry.quality}/5"
        }.toMutableList()

        // Si no hay registros, muestra un mensaje
        if (entryStrings.isEmpty()) {
            entryStrings.add("Aún no hay registros. ¡Añade uno en el Diario!")
        }

        // 2. Usar un ArrayAdapter para conectar la lista de Strings con el ListView
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, // Un diseño simple predeterminado
            entryStrings
        )

        // 3. Asignar el adaptador al ListView
        historyListView.adapter = adapter
    }
}