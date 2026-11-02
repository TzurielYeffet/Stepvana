package app.stepvana.ui

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals

class DateConversionTest {

    @Test
    fun millisToDate_isCorrect(){
        val millis = LocalDate(2026, 10, 28).atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
        val localDate = LocalDate(2026,10,28)
        assertEquals(localDate,millisToLocalDate(millis))
    }

    @Test
    fun lateUtcEvening_staysSameDay(){
        val lateInDay = LocalDateTime(2026, 10, 28,23,0).toInstant(TimeZone.UTC).toEpochMilliseconds()
        val localDate = LocalDate(2026,10,28)
        assertEquals(localDate,millisToLocalDate(lateInDay))
    }
}