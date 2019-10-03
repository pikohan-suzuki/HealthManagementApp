package com.amebaownd.pikohan_nwiatori.healthmanagementapp.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.Data.Dao.FoodStuffDao
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.Data.Model.FoodStuff
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = arrayOf(FoodStuff::class),version = 1)
abstract class HealthDatabase:RoomDatabase(){
    abstract fun foodStuffDao() : FoodStuffDao

    companion object{
        @Volatile
        private var INSTANCE : HealthDatabase?=null

        fun getDatabase(
            context: Context,
            scope:CoroutineScope
        ):HealthDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HealthDatabase::class.java,
                    "health_database"
                )
                    .addCallback(RoomDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

    private class RoomDatabaseCallback(
        private val scope:CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onOpen(db:SupportSQLiteDatabase){
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