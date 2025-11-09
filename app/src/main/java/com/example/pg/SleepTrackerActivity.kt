package com.example.pg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.app.TimePickerDialog // ✅ Importación de TimePickerDialog
import java.util.Calendar // ✅ Importación de Calendar
import com.example.pg.SleepEntry // ✅ Importación de la estructura de datos
import com.example.pg.SleepData // ✅ Importación del almacén de datos (Historial)

class SleepTrackerActivity : AppCompatActivity() {

    // 1. DECLARACIÓN DE VARIABLES (PROPIEDADES DE LA CLASE)
    private lateinit var bedtimeEditText: EditText
    private lateinit var wakeupTimeEditText: EditText
    private lateinit var qualityEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var viewHistoryButton: Button

    // ✅ VARIABLES FALTANTES para los botones del TimePicker
    private lateinit var selectBedtimeButton: Button
    private lateinit var selectWakeupTimeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_tracker)

        // 2. ENCONTRAR ELEMENTOS (DENTRO DE onCreate)
        bedtimeEditText = findViewById(R.id.et_bedtime)
        wakeupTimeEditText = findViewById(R.id.et_wakeup_time)
        qualityEditText = findViewById(R.id.et_quality)
        saveButton = findViewById(R.id.btn_save_entry)
        viewHistoryButton = findViewById(R.id.btn_view_history)
        selectBedtimeButton = findViewById(R.id.btn_select_bedtime) // ✅ Inicializado
        selectWakeupTimeButton = findViewById(R.id.btn_select_wakeup_time) // ✅ Inicializado

        // 3. ASIGNAR LISTENERS

        // Lógica para la HORA DE ACOSTARSE (Abre el selector)
        selectBedtimeButton.setOnClickListener {
            showTimePicker(bedtimeEditText, selectBedtimeButton, "Acostarse")
        }

        // Lógica para la HORA DE LEVANTARSE (Abre el selector)
        selectWakeupTimeButton.setOnClickListener {
            showTimePicker(wakeupTimeEditText, selectWakeupTimeButton, "Levantarse")
        }

        // Botón "Guardar Registro"
        saveButton.setOnClickListener {
            saveSleepEntry()
        }

        // Botón "Ver Historial"
        viewHistoryButton.setOnClickListener {
            val intent = Intent(this, SleepHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    // 4. FUNCIÓN PARA EL SELECTOR DE HORA (FUERA DE onCreate)
    private fun showTimePicker(targetEditText: EditText, targetButton: Button, title: String) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val timeString = String.format("%02d:%02d", selectedHour, selectedMinute)

                // Guarda el valor en el EditText oculto
                targetEditText.setText(timeString)

                // Actualiza el texto del botón que ve el usuario
                targetButton.text = "Hora de $title: $timeString"
            },
            hour,
            minute,
            false // Formato 12 horas (true sería 24 horas)
        )
        timePickerDialog.setTitle("Selecciona la Hora de $title")
        timePickerDialog.show()
    }


    // 5. FUNCIÓN PARA GUARDAR DATOS (FUERA DE onCreate)
    private fun saveSleepEntry() {

        val bedtime = bedtimeEditText.text.toString()
        val wakeupTime = wakeupTimeEditText.text.toString()
        val quality = qualityEditText.text.toString()

        if (bedtime.isBlank() || wakeupTime.isBlank() || quality.isBlank()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }

        val currentDate = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date())

        val newEntry = SleepEntry(currentDate, bedtime, wakeupTime, quality)

        // Guarda el registro en la lista estática
        SleepData.sleepEntries.add(newEntry)

        val summaryMessage = "¡Registro Guardado para el $currentDate!\n" +
                "Horas: $bedtime a $wakeupTime. Calidad: $quality/5."

        Toast.makeText(this, summaryMessage, Toast.LENGTH_LONG).show()

        bedtimeEditText.text.clear()
        wakeupTimeEditText.text.clear()
        qualityEditText.text.clear()
    }
}