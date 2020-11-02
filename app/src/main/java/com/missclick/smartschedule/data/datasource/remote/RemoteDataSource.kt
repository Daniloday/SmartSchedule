package com.missclick.smartschedule.data.datasource.remote

import android.location.GnssAntennaInfo
import android.util.Log
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.missclick.smartschedule.data.datasource.local.entity.DayEntity
import com.missclick.smartschedule.data.datasource.local.entity.LessonEntity
import com.missclick.smartschedule.data.datasource.remote.remoteModels.ScheduleFB
import com.missclick.smartschedule.data.repository.LessonRepository

class RemoteDataSource {

    var dbRef : DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        dbRef = db.getReference("Schedule")
    }

    fun addScheduleToFirebase(lessons : List<LessonEntity>, dayEntities: List<DayEntity>):String? {
        val idLog = dbRef.push().key
//        if (idLog != null) {
//            for(lesson in lessons)
//                dbRef.child(idLog).child("Lessons").child(lesson.id.toString()).setValue(lesson)
//            for(day in dayEntities)
//                dbRef.child(idLog).child("Days").child(day.id.toString()).setValue(day)
//            return idLog
//        }
        if (idLog != null) {
            dbRef.child(idLog).setValue(ScheduleFB(lessons = lessons, days = dayEntities))
            return idLog
        }
        return null
    }

    fun importScheduleFromFirebase(id : String, callback : LessonRepository.Callback){
        dbRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                val schedule : ScheduleFB = snapshot.getValue<ScheduleFB>()!!
                callback.insertDaysAndLesson(schedule = schedule)
            }
        })
    }


}