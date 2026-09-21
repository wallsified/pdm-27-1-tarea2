package com.example.tarea2_remindme

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DiscardedTasksActivity : AppCompatActivity() {

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_discarded_tasks)

        val lvDiscardedTasks = findViewById<ListView>(R.id.lvDiscardedTasks)
        val btnBack = findViewById<Button>(R.id.btnBack)

        adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, TaskRepository.discardedTasks)
        lvDiscardedTasks.adapter = adapter

        // Volver a pantalla principal
        btnBack.setOnClickListener {
            finish()
        }

        // Restaurar tarea al hacer clic
        lvDiscardedTasks.setOnItemClickListener { _, _, position, _ ->
            val task = TaskRepository.discardedTasks[position]
            TaskRepository.currentTasks.add(task.replace("❌", "📌"))
            TaskRepository.discardedTasks.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }
}
