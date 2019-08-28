package com.suonk.socialmusician.model.ModelDB

import androidx.room.*

/**
 * Data class qui représente un détail d'un musician par exemple un mail, numero de téléphone, etc...
 * @author Kenzy Suon
 */
@Entity(tableName = "musician_details_table",
        foreignKeys = [
            ForeignKey(entity = MusicianDB::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("id_musician"),
                    onDelete = ForeignKey.CASCADE)])

data class MusicianDetailsDB(

        /**
         * Id du musician Detail.
         */
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int?,

        /**
         * Id du [musician][MusicianDB].
         */
        @ColumnInfo(name = "id_musician") var idMusician: Int?,

        /**
         * Contenu du musician detail exemple: "06 24 25 45 .." ou "test.test@gmail.com"
         */
        @ColumnInfo(name = "content") val content: String,

        /**
         * Type du "contenu" exemple: phoneNumber ou mail
         */
        @ColumnInfo(name = "type") val type: String,

        /**
         * Description du musician detail"
         */
        @ColumnInfo(name = "description") val description: String,

        /**
         * Instrument du musician detail exemple: Paris, France ou Pékin, Chine"
         */
        @ColumnInfo(name = "localisation") val localisation: String,

        /**
         * Instrument du musician detail exemple: Guitare, Basse, Trompette, Saxophone etc..."
         */
        @ColumnInfo(name = "instrument") val instrument: String,

        /**
         * val musicStyle: MusicStyle
         */
        @ColumnInfo(name = "musicStyle") val musicStyle: Int
)