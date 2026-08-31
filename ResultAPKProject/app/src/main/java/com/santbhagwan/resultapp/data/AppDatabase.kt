package com.santbhagwan.resultapp.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Student::class,Mark::class],version=1,exportSchema=false)
abstract class AppDatabase:RoomDatabase(){ abstract fun students():StudentDao; abstract fun marks():MarkDao
 companion object { @Volatile private var INSTANCE:AppDatabase?=null
  fun get(c:Context)=INSTANCE?:synchronized(this){INSTANCE?:Room.databaseBuilder(c,AppDatabase::class.java,"result.db").build().also{INSTANCE=it}}
 }
}
