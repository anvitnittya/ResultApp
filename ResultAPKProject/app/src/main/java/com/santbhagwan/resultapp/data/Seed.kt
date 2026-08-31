package com.santbhagwan.resultapp.data
import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

suspend fun seedIfEmpty(context:Context,db:AppDatabase){ withContext(Dispatchers.IO){
 if(db.students().get(1)!=null) return@withContext
 val students=mutableListOf<Student>(); context.assets.open("students.csv").use{BufferedReader(InputStreamReader(it)).useLines{ls->ls.drop(1).forEach{l->val p=l.split(','); if(p.size>=6) students+=Student(p[0].toInt(),p[1],p[2],p[3],p[4],p[5])}}}
 db.students().insertAll(students)
 val marks=mutableListOf<Mark>(); context.assets.open("marks.csv").use{BufferedReader(InputStreamReader(it)).useLines{ls->ls.drop(1).forEach{l->val p=l.split(','); if(p.size>=7) marks+=Mark(p[0].toInt(),p[1],p[2].toDoubleOrNull()?:0.0,p[3].toDoubleOrNull()?:0.0,p[4].toDoubleOrNull()?:0.0,p[5].toDoubleOrNull()?:0.0,p[6].toDoubleOrNull()?:0.0)}}}
 db.marks().insertAll(marks)
}}
