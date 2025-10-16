package com.example.pg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.app.TimePickerDialog
import java.util.Calendar
import com.example.pg.SleepEntry

class SleepTrackerActivity : AppCompatActivity() {

    // Declaramos las variables.
    private lateinit var bedtimeEditText: EditText
    private lateinit var wakeupTimeEditText: EditText
    private lateinit var qualityEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var viewHistoryButton: Button // 🌟 NUEVO: Botón para ver el historial

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_tracker)

        // 1. Encontrar los elementos de la interfaz por su ID
        bedtimeEditText = findViewById(R.id.et_bedtime)
        wakeupTimeEditText = findViewById(R.id.et_wakeup_time)
        qualityEditText = findViewById(R.id.et_quality)
        saveButton = findViewById(R.id.btn_save_entry)
        viewHistoryButton = findViewById(R.id.btn_view_history) // 🌟 NUEVO: Inicializar el botón de historial

        // 2. Asignar un Listener de Click al botón "Guardar Registro"
        saveButton.setOnClickListener {
            saveSleepEntry() // Llama a la función que procesa y guarda los datos
        }

        // 3. Asignar un Listener de Click al botón "Ver Historial"
        viewHistoryButton.setOnClickListener {
            val intent = Intent(this, SleepHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    // Función que se encarga de recoger, validar y GUARDAR los datos
    private fun saveSleepEntry() {

        val bedtime = bedtimeEditText.text.toString()
        val wakeupTime = wakeupTimeEditText.text.toString()
        val quality = qualityEditText.text.toString()

        // 4. Validación de datos (simple)
        if (bedtime.isBlank() || wakeupTime.isBlank() || quality.isBlank()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        // 🌟 CÓDIGO AÑADIDO: Obtener la fecha actual y guardar el registro
        val currentDate = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date())

        // Crear el objeto SleepEntry
        val newEntry = SleepEntry(currentDate, bedtime, wakeupTime, quality)

        // ** GUARDAR el registro en nuestra lista estática (simulación de DB) **
        SleepData.sleepEntries.add(newEntry)

        // 5. Mostrar el resumen
        val summaryMessage = "¡Registro Guardado para el $currentDate!\n" +
                "Horas: $bedtime a $wakeupTime. Calidad: $quality/5."

        Toast.makeText(this, summaryMessage, Toast.LENGTH_LONG).show()

        // 6. Limpiar los campos
        bedtimeEditText.text.clear()
        wakeupTimeEditText.text.clear()
        qualityEditText.text.clear()
    }
}