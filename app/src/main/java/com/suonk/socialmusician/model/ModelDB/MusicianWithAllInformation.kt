package com.suonk.socialmusician.model.ModelDB

import android.content.Context
import androidx.room.Embedded
import androidx.room.Relation
import com.suonk.socialmusician.model.MusiciansRoomDatabase


class MusicianWithAllInformation {
    @Embedded
    var musicianDB: MusicianDB? = null

    @Relation(parentColumn = "id", entityColumn = "id_musician", entity = MusicianDetailsDB::class)
    var musicianDetailsList: List<MusicianDetailsDB>? = null

    fun getMusicianId(): Int {
        return musicianDB!!.id!!
    }

    fun getPhoneNumber(): String {
        val regex = "((\\+33)|0|(\\+33 )){1}([67]){1}(( [0-9]{2})|([0-9]{2})){4}".toRegex()
        var onlyFix = ""
        for (detail in musicianDetailsList!!) {
            println(detail.content + "matches with regex ?" + detail.content.matches(regex))
            if (detail.type == "phone" && detail.content.matches(regex)) {
                return detail.content
            } else if (detail.type == "phone" && onlyFix == "") {
                onlyFix = detail.content
            }
        }
        return onlyFix
    }

    fun getFirstMail(): String {
        for (detail in musicianDetailsList!!) {
            if (detail.type == "mail") {
                return detail.content
            }
        }
        return ""
    }

    override fun equals(other: Any?): Boolean {
        return if (other is MusicianWithAllInformation) {
            if (other.musicianDB == this.musicianDB) {
                println("is printing true")
                true
            } else {
                false
            }
        } else {
            false
        }
    }
}
