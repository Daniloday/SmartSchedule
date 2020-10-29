package com.missclick.smartschedule.data.datasource.remote

import android.util.Log
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity

class RemoteDataSource {

    var dbRef : DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        dbRef = db.getReference("Schedule")
    }

    fun addScheduleToFirebase(lessons : List<LessonEntity>, dayEntities: List<DayEntity>):String? {
        val idLog = dbRef.push().key
        if (idLog != null) {
            for(lesson in lessons)
                dbRef.child(idLog).child("Lessons").child(lesson.id.toString()).setValue(lesson)
            for(day in dayEntities)
                dbRef.child(idLog).child("Days").child(day.id.toString()).setValue(day)
            return idLog
        }
        return null
    }

    fun importScheduleFromFirebase(id : String){
        val options = FirebaseRecyclerOptions.Builder<LessonEntity>()
            .setQuery(dbRef.child(id).child("Lessons")
            ) { snapshot ->
                LessonEntity(
                    id = snapshot.child("id").value as Int?,
                    name = snapshot.child("name").value.toString(),
                    teacherName = snapshot.child("teacherName").value.toString(),
                    type = snapshot.child("type").value.toString(),
                    links = snapshot.child("links").value.toString(),
                    description = snapshot.child("description").value.toString()
                )
            }
            .build()

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val a = snapshot.value
                Log.e("logImport", a.toString())
            }
        })

    }


}