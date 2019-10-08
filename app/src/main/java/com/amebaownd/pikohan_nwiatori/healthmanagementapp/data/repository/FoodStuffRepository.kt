package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.dao.FoodStuffDao
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff

class FoodStuffRepository(private val foodStuffDao: FoodStuffDao) {
    val allFoodStuffs: LiveData<List<FoodStuff>> = foodStuffDao.getAllFoodStuffs()
//    var _foodStuff:LiveData<FoodStuff> = foodStuffDao.getById("74d5f3b5-2628-4f4d-b30b-fefe64f27045")
//        foodStuffDao.getById("74d5f3b5-2628-4f4d-b30b-fefe64f27045")
    suspend fun insert(foodStuff: FoodStuff) {
        foodStuffDao.insert(foodStuff)
    }

    suspend fun update(foodStuff: FoodStuff){
        foodStuffDao.update(foodStuff)
    }

    suspend fun delete(foodStuff: FoodStuff){
        foodStuffDao.delete(foodStuff)
    }

     fun loadFoodStuff(id: String):FoodStuff {
//        val aaa = foodStuffDao.getById(id)
//        val aaaaa = foodStuffDao.getById2(id)
//
//        val aaaa =foodStuffDao.getAllFoodStuffs()
//        val bbb = aaa.value
//        val bbbb=aaaa.value?.filter { it.id ==id }
//        val ccc =allFoodStuffs.value
//        val cccc = ccc?.filter { it.id==id }
     return foodStuffDao.getById(id)
    }
}