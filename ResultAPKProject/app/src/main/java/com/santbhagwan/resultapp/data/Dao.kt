package com.santbhagwan.resultapp.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao interface StudentDao {
 @Query("SELECT * FROM students ORDER BY roll") fun all(): Flow<List<Student>>
 @Query("SELECT * FROM students WHERE roll=:roll LIMIT 1") suspend fun get(roll:Int): Student?
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun insertAll(items:List<Student>)
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun insert(item:Student)
}
@Dao interface MarkDao {
 @Query("SELECT * FROM marks WHERE roll=:roll ORDER BY subject") fun forStudent(roll:Int): Flow<List<Mark>>
 @Query("SELECT * FROM marks WHERE roll=:roll AND subject=:subject LIMIT 1") suspend fun get(roll:Int,subject:String):Mark?
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun insertAll(items:List<Mark>)
 @Insert(onConflict=OnConflictStrategy.REPLACE) suspend fun insert(item:Mark)
}
