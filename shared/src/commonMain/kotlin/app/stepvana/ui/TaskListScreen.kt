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
import app.stepvana.domain.Task

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onTaskCheckedChange: (Long, Boolean) -> Unit,
    onAddTask: (String) -> Unit,
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
                Task(id = 1, title = "Task 1"),
                Task(id = 2, title = "Task 2"),
                Task(id = 3, title = "Task 3"),
                Task(id = 4, title = "Task 4"),
                Task(id = 5, title = "Task 5"),
                Task(id = 6, title = "Task 6"),
                Task(id = 7, title = "Task 7"),
            ),
            onTaskCheckedChange = { _, _ -> },
            onAddTask = {},
        )
    }
}