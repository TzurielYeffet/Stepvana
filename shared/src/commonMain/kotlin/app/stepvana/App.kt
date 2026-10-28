package app.stepvana

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import app.stepvana.ui.TaskListScreen
import app.stepvana.ui.TaskListViewModel

@Composable
fun App() {
    val viewModel = viewModel { TaskListViewModel() }
    val tasks by viewModel.tasks.collectAsStateWithLifecycle()
    MaterialTheme {
        TaskListScreen(
            tasks = tasks,
            onTaskCheckedChange = viewModel::onTaskCheckedChange,
            onAddTask = viewModel::addTask,
        )
    }

}
