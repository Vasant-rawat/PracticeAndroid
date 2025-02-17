package com.example.weather.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weather.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query(value = "SELECT * FROM fav_tbl")
   fun getFavorites(): Flow<List<Favorite>>

  @Query(value = "SELECT * FROM fav_tbl WHERE city=:city")
   suspend fun getFavById(city:String):Flow<Favorite>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertFavorites(favorite: Favorite)
   @Update(onConflict = OnConflictStrategy.REPLACE)
   suspend fun updateFavorites(favorite: Favorite)

   @Query(value = "DELETE FROM fav_tbl")
   suspend fun deleteAllFavorites()

  @Delete
   suspend fun deleteFavorites(favorite: Favorite)
}