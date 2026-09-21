package com.example.tarea2_remindme

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView

class TaskAdapter(
    private val context: Context,
    private val dataSource: MutableList<TaskItem>,
    private val onDeleteClickListener: (Int) -> Unit
) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int = dataSource.size

    override fun getItem(position: Int): Any = dataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.item_task, parent, false)

        val cbTask = rowView.findViewById<CheckBox>(R.id.cbTask)
        val tvTaskText = rowView.findViewById<TextView>(R.id.tvTaskText)
        val btnDeleteTask = rowView.findViewById<ImageButton>(R.id.btnDeleteTask)

        val taskItem = dataSource[position]

        tvTaskText.text = taskItem.text
        cbTask.isChecked = taskItem.isChecked

        if (taskItem.isChecked) {
            tvTaskText.paintFlags = tvTaskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            tvTaskText.setTextColor(0x8B888888.toInt())
        } else {
            tvTaskText.paintFlags = tvTaskText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            tvTaskText.setTextColor(context.getColor(android.R.color.white))
        }

        btnDeleteTask.setOnClickListener {
            onDeleteClickListener(position)
        }

        return rowView
    }
}
