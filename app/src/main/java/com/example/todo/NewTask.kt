package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewTask : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_task)

        val titleInput: EditText = findViewById(R.id.taskTitle)
        val deadlineInput: EditText = findViewById(R.id.editTextDate)
        val descriptionInput: EditText = findViewById(R.id.Description)

        findViewById<Button>(R.id.Done).setOnClickListener {
            val summary = buildTaskSummary(
                titleInput.text.toString(),
                deadlineInput.text.toString(),
                descriptionInput.text.toString()
            )
            if (summary == null) {
                titleInput.error = getString(R.string.title_required)
                titleInput.requestFocus()
                return@setOnClickListener
            }

            setResult(RESULT_OK, Intent().putExtra(EXTRA_TASK, summary))
            finish()
        }

        findViewById<Button>(R.id.Back).setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    internal companion object {
        const val EXTRA_TASK = "com.example.todo.EXTRA_TASK"
    }
}
