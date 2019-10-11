package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.dao.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(
    FoodStuff::class,
    Dish::class,
    DishItem::class,
    Aerobic::class,
    AerobicHard::class,
    Muscle::class,
    Schedule::class,
    CompletedSchedule::class,
    Stump::class), version = 4)
abstract class HealthDatabase : RoomDatabase() {
    abstract fun foodStuffDao(): FoodStuffDao
    abstract fun dishDao(): DishDao
    abstract fun aerobicDao():AerobicDao
    abstract fun mascleDao():MascleDao
    abstract fun scheduleDao():ScheduleDao
    abstract fun stumpDao():StumpDao
    abstract fun todayDao():TodayDao

    companion object {
        @Volatile
        private var INSTANCE: HealthDatabase? = null

        fun getDatabase(
            context: Context
        ): HealthDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealthDatabase::class.java,
                    "health_database"
                )
                    .addMigrations(
                        MIGRATION1_2,
                        MIGRATION2_3,
                        MIGRATION3_4)
                    //.addCallback(RoomDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }

        private val MIGRATION1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE dish_table (" +
                            " dish_id TEXT NOT NULL , " +
                            " dish_name TEXT NOT NULL," +
                            " dish_group INTEGER NOT NULL ," +
                            " total_calorie REAL NOT NULL ," +
                            " total_protein REAL NOT NULL ," +
                            " total_carbohydrate REAL NOT NULL ," +
                            " total_fat REAL NOT NULL , " +
                            " PRIMARY KEY ( dish_id ) )"
                )
            }
        }
        private val MIGRATION2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE dish_item_table (" +
                            " dish_id TEXT NOT NULL ," +
                            " foodstuff_id TEXT NOT NULL, " +
                            " is_gram INTEGER NOT NULL, " +
                            " amount REAL NOT NULL , " +
                            "PRIMARY KEY( dish_id,foodstuff_id)," +
                            " FOREIGN KEY (dish_id) REFERENCES dish_table(dish_id) ON UPDATE CASCADE ON DELETE CASCADE," +
                            " FOREIGN KEY (foodstuff_id) REFERENCES foodstuff_table(foodstuff_id) ON UPDATE CASCADE ON DELETE CASCADE )"
                )
            }
        }
        private val MIGRATION3_4 = object :Migration(3,4){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE aerobic_table (" +
                            " aerobic_id TEXT NOT NULL ," +
                            " added_weight REAL NOT NULL, " +
                            "PRIMARY KEY( aerobic_table) )"
                )
                database.execSQL(
                    "CREATE TABLE hard_table (" +
                            " aerobic_id TEXT NOT NULL ," +
                            " hard_name TEXT NOT NULL, " +
                            " mets REAL NOT NULL"+
                            " PRIMARY KEY( aerobic_table,hard_name)," +
                            " FOREIGN KEY(aerobic_id) REFERENCES aerobic_table(aerobic_id) ON UPDATE CASCADE ON DELETE CASCADE )"
                )
                database.execSQL(
                    "CREATE TABLE muscle_table (" +
                            " muscle_id TEXT NOT NULL ," +
                            " name TEXT NOT NULL, " +
                            " parts_category INTEGER NOT NULL " +
                            " training_category INTEGER NOT NULL" +
                            "PRIMARY KEY( muscle_id) )"
                )
                database.execSQL(
                    "CREATE TABLE schedule_table (" +
                            " schedule_id TEXT NOT NULL ," +
                            " name TEXT NOT NULL, " +
                            " training_id TEXT NOT NULL " +
                            " is_repeat INTEGER NOT NULL " +
                            " start_date LONG NOT NULL " +
                            " end_date LONG NOT NULL "+
                            " PRIMARY KEY( schedule_id)," +
                            " FOREIGN KEY(training_id) REFERENCES muscle_table(muscle_id) ON UPDATE CASCADE ON DELETE CASCADE )"
                )
                database.execSQL(
                    "CREATE TABLE completed_schedule_table (" +
                            " muscle_id TEXT NOT NULL ," +
                            " date LONG NOT NULL, " +
                            " dish_group INTEGER NOT NULL"+
                            " PRIMARY KEY( muscle_id,date)," +
                            " FOREIGN KEY(muscle_id) REFERENCES muscle_table(muscle_id) ON UPDATE CASCADE ON DELETE CASCADE )"
                )
                database.execSQL(
                    "CREATE TABLE stump_table (" +
                            " stump_id TEXT NOT NULL ," +
                            " date LONG NOT NULL, " +
                            " stump_category INTEGER NOT NULL " +
                            "PRIMARY KEY( stump_id) )"
                )
            }
        }
    }

    private class RoomDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.foodStuffDao())
                }
            }
        }

        suspend fun populateDatabase(foodStuffDao: FoodStuffDao) {
            foodStuffDao.deleteAll()

            val foodstuff = FoodStuff(
                name = "carrot",
                food_group = 1,
                weight = 80,
                kcal_per = 20f,
                protein_per = 1f,
                carbohydrate_per = 2f,
                fat_per = 0.1f
            )
            foodStuffDao.insert(foodstuff)
        }
    }


}