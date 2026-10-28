package app.stepvana.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.stepvana.domain.Task

@Composable
fun TaskList(
    tasks: List<Task>,
    onTaskCheckedChange: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier) {
        itemsIndexed(
            items = tasks,
            key = { _, task -> task.id },
            ) { index, task ->
            TaskItem(
                task = task,
                onCheckedChange = { checked -> onTaskCheckedChange(task.id, checked) },
            )
            if (index < tasks.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}
