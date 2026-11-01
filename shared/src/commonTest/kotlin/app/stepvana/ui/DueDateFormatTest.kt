package app.stepvana.ui

import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class DueDateFormatTest {
    private val today = LocalDate(2026, 10, 28)

    @Test
    fun sameDay_isToday() {
        assertEquals("Today", formatDueDate(today, today))
    }

    @Test
    fun monthBoundary_isTomorrow() {
        val today = LocalDate(2026, 10, 31)
        val tomorrow = LocalDate(2026, 11, 1)
        assertEquals("Tomorrow", formatDueDate(today = today, date = tomorrow))
    }

    @Test
    fun yearBoundary_isTomorrow() {
        val today = LocalDate(2026, 12, 31)
        val tomorrow = LocalDate(2027, 1, 1)
        assertEquals("Tomorrow", formatDueDate(today = today, date = tomorrow))
    }

    @Test
    fun previousDay_isYesterday() {
        val today = LocalDate(2026, 10, 28)
        val yesterday = LocalDate(2026, 10, 27)
        assertEquals("Yesterday", formatDueDate(today = today, date = yesterday))
    }

    @Test
    fun dateFormat_isMonthAndDay() {
        val date = LocalDate(2026, 9, 29)
        assertEquals("Sep 29" , formatDueDate(today = today, date = date))
    }

    @Test
    fun dateFormat_isMonthAndDayWithoutLeadingZero() {
        val date = LocalDate(2026,10,9)
        assertEquals("Oct 9" , formatDueDate(today = today, date = date))
    }
}