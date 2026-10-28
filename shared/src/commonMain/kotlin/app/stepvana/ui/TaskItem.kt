package app.stepvana.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.stepvana.domain.Priority
import app.stepvana.domain.Task

@Composable
fun TaskItem(task: Task, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onCheckedChange,
        )
        Spacer(Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium,
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
            )

            Text(
                text = task.dueDate?.toString() ?: "No date",
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Spacer(
            modifier = Modifier.width(8.dp),
        )
        Text(
            text = task.priority.label,
        )

    }
}

@Preview
@Composable
private fun TaskItemPreviewDate() {
    MaterialTheme {
        TaskItem(
            task = Task(
                id = 1,
                title = "Task 1",
                description = "Description 1",
                priority = Priority.HIGH,
                dueDate = 1620000000000,
                isCompleted = false,
            ),
            onCheckedChange = {},
        )
    }
}

@Preview
@Composable
private fun TaskItemPreviewNoDate() {
    MaterialTheme {
        TaskItem(
            task = Task(
                id = 2,
                title = "Task 2",
            ),
            onCheckedChange = {},
        )
    }
}

@Preview
@Composable
private fun TaskItemPreviewVeryLongTitle() {
    MaterialTheme {
        TaskItem(
            task = Task(
                id = 3,
                title = "Buy paint, rollers, tape and drop cloths for the bedroom and hallway before the weekend",
            ),
            onCheckedChange = {},
        )
    }
}

@Preview
@Composable
private fun TaskItemInteractivePreview() {
    var task by remember { mutableStateOf(Task(id = 1, title = "Task 1")) }
    MaterialTheme {
        TaskItem(
            task = task,
            onCheckedChange = { checked ->
                task = task.copy(isCompleted = checked)
            },
        )
    }
}
