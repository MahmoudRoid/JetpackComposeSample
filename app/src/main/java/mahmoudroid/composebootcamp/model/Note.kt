package mahmoudroid.composebootcamp.model

import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

data class Note(
    val uuid: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
    val date: Date = Date()
)