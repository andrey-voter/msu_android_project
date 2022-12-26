package todoList.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import todoList.di.ApplicationScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {

            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch{
                dao.insert(Task("Make this app"))
                dao.insert(Task("Add this task", priority = true))
                dao.insert(Task("Cook some fish"))
                dao.insert(Task("Robber a bank"))
                dao.insert(Task("Save the world", completed = true))
                dao.insert(Task("Learn to ride a horse"))
                dao.insert(Task("Just one more task"))

            }
        }
    }
}