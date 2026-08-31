package com.santbhagwan.resultapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="students")
data class Student(@PrimaryKey val roll:Int,val name:String,val grNo:String="",val dob:String="",val guardian:String="",val address:String="")

@Entity(tableName="marks", primaryKeys=["roll","subject"])
data class Mark(val roll:Int,val subject:String,val ut1:Double=0.0,val theory1:Double=0.0,val ut2:Double=0.0,val theory2:Double=0.0,val practical:Double=0.0){
    val termA get()=ut1+theory1
    val termB get()=ut2+theory2+practical
    val total200 get()=termA+termB
    val average100 get()=kotlin.math.ceil(total200/2.0)
}
