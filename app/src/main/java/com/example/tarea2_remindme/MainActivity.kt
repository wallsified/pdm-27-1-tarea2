package com.example.tarea2_remindme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val lvTasks = findViewById<ListView>(R.id.lvTasks)
        val btnAddTask = findViewById<Button>(R.id.btnAddTask)
        val btnGoToDiscarded = findViewById<Button>(R.id.btnGoToDiscarded)

        adapter = TaskAdapter(this, TaskRepository.currentTasks) { position ->
            val taskItem = TaskRepository.currentTasks[position]
            TaskRepository.discardedTasks.add(TaskItem(taskItem.text.replace("📌", "❌")))
            TaskRepository.currentTasks.removeAt(position)
            adapter.notifyDataSetChanged()
        }
        lvTasks.adapter = adapter

        // Agregar tarea
        btnAddTask.setOnClickListener {
            val input = EditText(this)
            input.hint = "Escribe tu tarea aquí..."
            AlertDialog.Builder(this)
                .setTitle("Nueva Tarea")
                .setView(input)
                .setPositiveButton("Añadir") { _, _ ->
                    val text = input.text.toString()
                    if (text.isNotEmpty()) {
                        TaskRepository.currentTasks.add(TaskItem("📌 $text"))
                        adapter.notifyDataSetChanged()
                    }
                }.setNegativeButton("Cancelar", null).show()
        }

        // Alternar estado de completado al hacer clic en la fila (checklist)
        lvTasks.setOnItemClickListener { _, _, position, _ ->
            val taskItem = TaskRepository.currentTasks[position]
            taskItem.isChecked = !taskItem.isChecked
            adapter.notifyDataSetChanged()
        }

        // Navegar a descartadas
        btnGoToDiscarded.setOnClickListener {
            startActivity(Intent(this, DiscardedTasksActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
