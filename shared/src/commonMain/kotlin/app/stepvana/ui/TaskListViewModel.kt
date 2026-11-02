package app.stepvana.ui

import androidx.lifecycle.ViewModel
import app.stepvana.domain.Priority
import app.stepvana.domain.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn
import kotlin.time.Clock
private val fixToday = Clock.System.todayIn(TimeZone.currentSystemDefault())

private val sampleTasks = listOf(
    Task(
        id = 1,
        title = "Repaint the bedroom",
        description = "Light grey, two coats",
        priority = Priority.HIGH,
        dueDate = fixToday.plus(5, DateTimeUnit.DAY)
    ),
    Task(id = 2, title = "Buy groceries", priority = Priority.MEDIUM, dueDate = fixToday),
    Task(
        id = 3,
        title = "Call the dentist",
        priority = Priority.HIGH,
        dueDate = fixToday.plus(1, DateTimeUnit.DAY)
    ),
    Task(id = 4, title = "Read 20 pages", isCompleted = true),
    Task(
        id = 5,
        title = "Renew passport",
        description = "Check photo requirements first",
        priority = Priority.CRITICAL,
        dueDate = fixToday.plus(14, DateTimeUnit.DAY)
    ),
    Task(id = 6, title = "Fix the leaking kitchen tap", priority = Priority.MEDIUM),
    Task(
        id = 7,
        title = "Plan Dana's birthday party",
        priority = Priority.HIGH,
        dueDate = fixToday.plus(10, DateTimeUnit.DAY)
    ),
    Task(id = 8, title = "Back up phone photos"),
    Task(
        id = 9,
        title = "Pay electricity bill",
        priority = Priority.CRITICAL,
        dueDate = fixToday.plus(2, DateTimeUnit.DAY),
        isCompleted = true
    ),
    Task(id = 10, title = "Learn LazyColumn", priority = Priority.MEDIUM, dueDate = fixToday),
    Task(
        id = 11,
        title = "Clean out the garage and sort everything into keep, donate and throw away piles",
        priority = Priority.LOW,
        dueDate = fixToday.plus(21, DateTimeUnit.DAY)
    ),
    Task(id = 12, title = "Water the plants", dueDate = fixToday.minus(1, DateTimeUnit.DAY)),
    Task(
        id = 13,
        title = "Book a car service",
        priority = Priority.MEDIUM,
        dueDate = fixToday.plus(7, DateTimeUnit.DAY)
    ),
    Task(
        id = 14,
        title = "Reply to Yossi's email",
        priority = Priority.LOW,
        dueDate = fixToday.plus(1, DateTimeUnit.DAY)
    ),
    Task(id = 15, title = "Research standing desks"),
)

class TaskListViewModel : ViewModel() {
    private val _tasks = MutableStateFlow(sampleTasks)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _today = MutableStateFlow(Clock.System.todayIn(TimeZone.currentSystemDefault()))
    val today: StateFlow<LocalDate> = _today.asStateFlow()
    fun onTaskCheckedChange(id: Long, checked: Boolean) {
        _tasks.update { tasks -> tasks.map { if (it.id == id) it.copy(isCompleted = checked) else it } }
    }

    fun addTask(title: String, date: LocalDate?) {
        _tasks.update { current ->
            val nextId = (current.maxOfOrNull { it.id } ?: 0) + 1
            current + Task(id = nextId, title = title, dueDate = date)
        }
    }
}

