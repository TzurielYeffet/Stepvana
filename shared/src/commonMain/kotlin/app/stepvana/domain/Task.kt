package app.stepvana.domain

import kotlinx.datetime.LocalDate


enum class Priority(val label: String) {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    CRITICAL("Critical")
}

data class Task(
    val id: Long,
    val title: String,
    val description: String = "",
    val priority: Priority = Priority.LOW,
    val dueDate: LocalDate? = null,
    val isCompleted: Boolean = false,

    )
