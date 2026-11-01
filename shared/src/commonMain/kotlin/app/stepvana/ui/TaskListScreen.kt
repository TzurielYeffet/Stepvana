package app.stepvana.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.stepvana.domain.Priority
import app.stepvana.domain.Task
import kotlinx.datetime.LocalDate

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onTaskCheckedChange: (Long, Boolean) -> Unit,
    onAddTask: (String) -> Unit,
    today: LocalDate,
    modifier: Modifier = Modifier,
) {
    var showSheet by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = {
                Column {
                    Text(text = "Stepvana")
                    Text(
                        text = "${tasks.count { task -> task.isCompleted }} of ${tasks.size} done",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Text("+")
            }
        }
    ) { innerPadding ->
        TaskList(
            tasks = tasks,
            onTaskCheckedChange = onTaskCheckedChange,
            today = today,
            modifier = Modifier.padding(innerPadding)
        )
    }
    if (showSheet) {
        AddTaskSheet(
            onDismiss = { showSheet = false },
            onSave = { title ->
                onAddTask(title)
                showSheet = false
            },
        )
    }
}

@Preview
@Composable
private fun TaskListScreenPreview() {
    MaterialTheme {
        TaskListScreen(
            tasks = listOf(
                Task(id = 1, title = "Due today", priority = Priority.HIGH, dueDate = LocalDate(2026, 10, 28)),
                Task(id = 2, title = "Due tomorrow", priority = Priority.MEDIUM, dueDate = LocalDate(2026, 10, 29)),
                Task(id = 3, title = "Was due yesterday", priority = Priority.CRITICAL, dueDate = LocalDate(2026, 10, 27)),
                Task(id = 4, title = "Due later", dueDate = LocalDate(2026, 11, 9)),
                Task(id = 5, title = "No due date"),
                Task(id = 6, title = "Already done", dueDate = LocalDate(2026, 10, 28), isCompleted = true),
                Task(
                    id = 7,
                    title = "A very long title that has to wrap onto a second line in the list",
                    priority = Priority.LOW,
                ),
            ),
            onTaskCheckedChange = { _, _ -> },
            onAddTask = {},
            today = LocalDate(2026, 10, 29),
        )
    }
}
