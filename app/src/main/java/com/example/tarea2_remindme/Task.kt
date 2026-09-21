package com.example.tarea2_remindme

object TaskRepository {
    val currentTasks = mutableListOf<String>()
    val discardedTasks = mutableListOf<String>()

    init {
        // Tareas por defecto en español
        currentTasks.add("📌 Estudiar para el examen de Móviles")
        currentTasks.add("🛒 Comprar víveres del súper")
        currentTasks.add("💻 Proyecto de Desarrollo de Software")

        discardedTasks.add("❌ Hacer ejercicio por la mañana (Ejemplo)")
    }
}
