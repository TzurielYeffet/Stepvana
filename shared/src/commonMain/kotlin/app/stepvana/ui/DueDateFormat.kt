package app.stepvana.ui

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.minus
import kotlinx.datetime.plus

private val dateFormat = LocalDate.Format {
    monthName(MonthNames.ENGLISH_ABBREVIATED)
    char(' ')
    day(padding =Padding.NONE)
}

fun formatDueDate(date: LocalDate, today: LocalDate): String {
    return when (date) {
        today -> "Today"
        today.plus(value = 1, unit = DateTimeUnit.DAY) -> "Tomorrow"
        today.minus(value = 1, unit = DateTimeUnit.DAY) -> "Yesterday"
        else -> dateFormat.format(date)
    }
}