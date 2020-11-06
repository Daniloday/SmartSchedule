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
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketAddress

class RemoteDataSource {

    var dbRef : DatabaseReference

    init {
        val db = FirebaseDatabase.getInstance()
        dbRef = db.getReference("Schedule")
    }

    fun addScheduleToFirebase(lessons : List<LessonEntity>, dayEntities: List<DayEntity>): Deferred<String?> {
        return GlobalScope.async {
            val idLog = dbRef.push().key
            if (idLog != null) {
                dbRef.child(idLog).setValue(ScheduleFB(lessons = lessons, days = dayEntities))
                idLog
            }
            else null
        }
    }

    fun importScheduleFromFirebase(id : String, callback : LessonRepository.Callback){
        dbRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val schedule : ScheduleFB? = snapshot.getValue<ScheduleFB>()
                    if(schedule != null && schedule.lessons != null) callback.insertDaysAndLesson(schedule = schedule)
                } catch (e: IOException) {

                }

            }
        })
    }

    fun isOnlineAsync(): Deferred<Boolean> {
        return GlobalScope.async {
            try {
                val timeoutMs = 1500
                val sock = Socket()
                val sockaddr: SocketAddress = InetSocketAddress("8.8.8.8", 53)
                sock.connect(sockaddr, timeoutMs)
                sock.close()
                true
            } catch (e: IOException) {
                false
            }
        }
    }


}