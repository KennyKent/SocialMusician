package com.suonk.socialmusician.model.ModelDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class qui représente un contact
 * @author Kenzy Suon
 */
@Entity(tableName = "musician_table")
data class MusicianDB(

        /**
         * Id du contact.
         */
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Int?,

        /**
         * Prénom du contact.
         */
        @ColumnInfo(name = "first_name") val firstName: String,

        /**
         * Nom du contact.
         */
        @ColumnInfo(name = "last_name") var lastName: String,

        /**
         * Avatar utilisé lorsque le contact ne possède pas d'image de profile.
         */
        @ColumnInfo(name = "profile_picture") val profilePicture: Int,

        /**
         * Âge du contact.
         */
        @ColumnInfo(name = "age") val age: Int,

        /**
         * Image de profile du contact convertis en base 64.
         */
        @ColumnInfo(name = "profile_picture_str") val profilePicture64: String,

        /**
         * Le champ si Oui = 1 ou Non = 0, ce contact est un favori
         */
        @ColumnInfo(name = "is_friend") val isFriend: Int

)