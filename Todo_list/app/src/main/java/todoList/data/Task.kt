package todoList.data

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "task_table")
@Parcelize
data class Task @RequiresApi(Build.VERSION_CODES.O) constructor(
    val name: String,
    val priority: Boolean = false,
    val completed: Boolean = false,
    var deadline: Long = System.currentTimeMillis(),
//    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(deadline)
}