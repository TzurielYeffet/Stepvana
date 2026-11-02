package app.stepvana.ui

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant


fun millisToLocalDate(millis: Long): LocalDate {
    return Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.UTC).date
}