package com.example.pg

// 1. Clase de Datos: Define la estructura de cada registro de sueño.
data class SleepEntry(
    val date: String,
    val bedtime: String,
    val wakeupTime: String,
    val quality: String
)

// 2. Almacén de Datos (simulación de base de datos en memoria).
class SleepData {
    companion object {
        // La lista mutable que almacenará todos los registros.
        val sleepEntries = mutableListOf<SleepEntry>()
    }
}