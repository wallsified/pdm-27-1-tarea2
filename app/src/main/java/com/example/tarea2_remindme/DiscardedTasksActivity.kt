package com.example.tarea2_remindme

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DiscardedTasksActivity : AppCompatActivity() {

    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_discarded_tasks)

        val lvDiscardedTasks = findViewById<ListView>(R.id.lvDiscardedTasks)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Usamos el mismo TaskAdapter para unificar el estilo
        adapter = TaskAdapter(this, TaskRepository.discardedTasks) { position ->
            // Eliminar permanentemente al hacer clic en el botón de borrar
            TaskRepository.discardedTasks.removeAt(position)
            adapter.notifyDataSetChanged()
        }
        lvDiscardedTasks.adapter = adapter

        // Volver a pantalla principal
        btnBack.setOnClickListener {
            finish()
        }

        // Restaurar tarea al hacer clic en la fila
        lvDiscardedTasks.setOnItemClickListener { _, _, position, _ ->
            val task = TaskRepository.discardedTasks[position]
            TaskRepository.currentTasks.add(task)
            TaskRepository.discardedTasks.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }
}
