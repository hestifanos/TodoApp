package com.example.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val tasks = mutableListOf<String>()
    private lateinit var taskList: TextView
    private lateinit var searchView: SearchView

    private val taskEditor = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.getStringExtra(NewTask.EXTRA_TASK)?.let { tasks.add(it) }
            renderTasks()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        savedInstanceState?.getStringArrayList(KEY_TASKS)?.let(tasks::addAll)
        taskList = findViewById(R.id.taskList)
        searchView = findViewById(R.id.search)
        findViewById<Button>(R.id.AddTask).setOnClickListener {
            taskEditor.launch(Intent(this, NewTask::class.java))
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                renderTasks(newText.orEmpty())
                return true
            }
        })
        renderTasks()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putStringArrayList(KEY_TASKS, ArrayList(tasks))
        super.onSaveInstanceState(outState)
    }

    private fun renderTasks(query: String = searchView.query.toString()) {
        val visibleTasks = tasks.filter { it.contains(query.trim(), ignoreCase = true) }
        taskList.text = when {
            tasks.isEmpty() -> getString(R.string.no_tasks)
            visibleTasks.isEmpty() -> getString(R.string.no_matching_tasks)
            else -> visibleTasks.joinToString("\n\n") { "- $it" }
        }
    }

    private companion object {
        const val KEY_TASKS = "saved_tasks"
    }
}
