package com.systems.automaton.mindfullife.domain.use_case.tasks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.systems.automaton.mindfullife.R
import com.systems.automaton.mindfullife.domain.model.Task
import com.systems.automaton.mindfullife.presentation.tasks.TaskEvent
import com.systems.automaton.mindfullife.presentation.tasks.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFromShareActivity : ComponentActivity() {

    private val viewModel: TasksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent != null) {
            if (intent.action == Intent.ACTION_SEND && intent.type == "text/plain") {
                val title = intent.getStringExtra(Intent.EXTRA_TEXT)
                if (!title.isNullOrBlank()) {
                    viewModel.onEvent(
                        TaskEvent.AddTask(
                            Task(
                                title = title,
                                createdDate = System.currentTimeMillis(),
                                updatedDate = System.currentTimeMillis()
                            )
                        )
                    )
                    Toast.makeText(this, getString(R.string.added_task), Toast.LENGTH_SHORT)
                        .show()
                } else
                    Toast.makeText(this, getString(R.string.error_empty_title), Toast.LENGTH_SHORT)
                        .show()
            }
        }
        finish()
    }
}