package app.stepvana.ui

import androidx.lifecycle.ViewModel
import app.stepvana.domain.Priority
import app.stepvana.domain.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val DAY = 86_400_000L          // one day in milliseconds
private const val TODAY = 1_792_972_800_000L // 2026-10-26 00:00 UTC
private val sampleTasks = listOf(
    Task(
        id = 1,
        title = "Repaint the bedroom",
        description = "Light grey, two coats",
        priority = Priority.HIGH,
        dueDate = TODAY + 5 * DAY
    ),
    Task(id = 2, title = "Buy groceries", priority = Priority.MEDIUM, dueDate = TODAY),
    Task(id = 3, title = "Call the dentist", priority = Priority.HIGH, dueDate = TODAY + DAY),
    Task(id = 4, title = "Read 20 pages", isCompleted = true),
    Task(
        id = 5,
        title = "Renew passport",
        description = "Check photo requirements first",
        priority = Priority.CRITICAL,
        dueDate = TODAY + 14 * DAY
    ),
    Task(id = 6, title = "Fix the leaking kitchen tap", priority = Priority.MEDIUM),
    Task(
        id = 7,
        title = "Plan Dana's birthday party",
        priority = Priority.HIGH,
        dueDate = TODAY + 10 * DAY
    ),
    Task(id = 8, title = "Back up phone photos"),
    Task(
        id = 9,
        title = "Pay electricity bill",
        priority = Priority.CRITICAL,
        dueDate = TODAY + 2 * DAY,
        isCompleted = true
    ),
    Task(id = 10, title = "Learn LazyColumn", priority = Priority.MEDIUM, dueDate = TODAY),
    Task(
        id = 11,
        title = "Clean out the garage and sort everything into keep, donate and throw away piles",
        priority = Priority.LOW,
        dueDate = TODAY + 21 * DAY
    ),
    Task(id = 12, title = "Water the plants"),
    Task(
        id = 13,
        title = "Book a car service",
        priority = Priority.MEDIUM,
        dueDate = TODAY + 7 * DAY
    ),
    Task(
        id = 14,
        title = "Reply to Yossi's email",
        priority = Priority.LOW,
        dueDate = TODAY + DAY
    ),
    Task(id = 15, title = "Research standing desks"),
)

class TaskListViewModel : ViewModel() {
    private val _tasks = MutableStateFlow(sampleTasks)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun onTaskCheckedChange(id: Long, checked: Boolean) {
        _tasks.update { tasks -> tasks.map { if (it.id == id) it.copy(isCompleted = checked) else it } }
    }

    fun addTask(title: String) {
        _tasks.update { current ->
            val nextId = (current.maxOfOrNull { it.id } ?: 0) + 1
            current + Task(id = nextId, title = title)
        }
    }
}

