package com.santbhagwan.resultapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.santbhagwan.resultapp.data.*
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity:ComponentActivity(){
 override fun onCreate(b:Bundle?){super.onCreate(b); val db=AppDatabase.get(this); lifecycleScope.launch{seedIfEmpty(this@MainActivity,db)}; setContent{MaterialTheme{App(db)}}}
}

@Composable fun App(db:AppDatabase){
 var screen by remember{mutableStateOf("home")}; var selected by remember{mutableStateOf(1)}
 when(screen){
  "home"->Home({screen="students"},{screen="result"})
  "students"->StudentList(db,{selected=it;screen="result"},{screen="home"})
  else->ResultScreen(db,selected,{screen="students"})
 }
}
@Composable fun Home(goStudents:()->Unit,goResult:()->Unit){Column(Modifier.fillMaxSize().padding(24.dp),verticalArrangement=Arrangement.spacedBy(16.dp)){Text("Student Evaluation",style=MaterialTheme.typography.headlineMedium);Text("Sant Bhagwan Baba Kala va Vidnyan Junior College, Kharwandi Kasar");Text("11th Science • 2023-24");Button(onClick=goStudents,Modifier.fillMaxWidth()){Text("Student List")};Button(onClick=goResult,Modifier.fillMaxWidth()){Text("Result Card (Roll No. 1)")};Text("Offline result management app based on Result.xlsx",style=MaterialTheme.typography.bodySmall)}}
@Composable fun StudentList(db:AppDatabase,onSelect:(Int)->Unit,back:()->Unit){val students by db.students().all().collectAsState(emptyList());Column(Modifier.fillMaxSize().padding(16.dp)){Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text("Students",style=MaterialTheme.typography.headlineSmall);TextButton(back){Text("Home")}};LazyColumn{items(students){s->ListItem(headlineContent={Text("${s.roll}. ${s.name}")},supportingContent={Text("GR No: ${s.grNo}")},modifier=Modifier.fillMaxWidth()) {onSelect(s.roll)}}}}}
@Composable fun ResultScreen(db:AppDatabase,roll:Int,back:()->Unit){val marks by db.marks().forStudent(roll).collectAsState(emptyList());val student by produceState<Student?>(null,roll){value=db.students().get(roll)};Column(Modifier.fillMaxSize().padding(16.dp)){Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.SpaceBetween){Text("Result Card",style=MaterialTheme.typography.headlineSmall);TextButton(back){Text("Students")}};Text(student?.name?:("Roll $roll"),style=MaterialTheme.typography.titleLarge);Text("Roll No: $roll   GR No: ${student?.grNo.orEmpty()}");Spacer(Modifier.height(12.dp));LazyColumn{item{Row(Modifier.fillMaxWidth()){Text("Subject",Modifier.weight(1f));Text("Avg /100",Modifier.width(80.dp));Text("Remark",Modifier.width(80.dp))}};items(marks){m->Row(Modifier.fillMaxWidth().padding(vertical=8.dp)){Text(m.subject,Modifier.weight(1f));Text(String.format(Locale.US,"%.0f",m.average100),Modifier.width(80.dp));Text(if(m.average100>=35)"Pass" else "Fail",Modifier.width(80.dp))}};item{val total=marks.sumOf{it.average100};Text("Total (core subjects): ${String.format(Locale.US,"%.0f",total)}",style=MaterialTheme.typography.titleMedium);Text("Percentage base: 600 (as in workbook)");Text("Overall: ${String.format(Locale.US,"%.2f",total/6.0)}%")}}}}
