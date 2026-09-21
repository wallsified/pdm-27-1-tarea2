package com.example.tarea2_remindme

data class TaskItem(
    var text: String,
    var isChecked: Boolean = false
) {
    override fun toString(): String = text
}

object TaskRepository {
    val currentTasks = mutableListOf<TaskItem>()
    val discardedTasks = mutableListOf<TaskItem>()

    init {
        currentTasks.add(TaskItem("Estudiar para el exámen de Concurrente"))
        currentTasks.add(TaskItem("Comprar leche en el súper"))
        currentTasks.add(TaskItem("Proyecto de Desarrollo de Software"))
        discardedTasks.add(TaskItem("Enviar correo de avance de tesis"))
    }
}
