package com.suonk.socialmusician.model

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import android.content.Context
import com.suonk.socialmusician.model.ModelDB.*
import com.suonk.socialmusician.model.requestDB.*

/**
 * La Classe qui permet de créer la base de données et de la garder à jour
 * @author Kenzy Suon
 */
@Database(entities = [MusicianDB::class, MusicianDetailsDB::class], version = 1)
abstract class MusiciansRoomDatabase : RoomDatabase() {
    abstract fun musiciansDao(): MusiciansDao
    abstract fun musiciansDetailsDao(): MusicianDetailsDao

    companion object {
        private var INSTANCE: MusiciansRoomDatabase? = null

        //creation de la base de données
        fun getDatabase(context: Context): MusiciansRoomDatabase? {
            if (INSTANCE != null) {
                return INSTANCE
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MusiciansRoomDatabase::class.java,
                        "Musician_database")
//                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                return INSTANCE
            }
        }

//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE musician_table " + " ADD COLUMN mail TEXT DEFAULT '' NOT NULL")
//            }
//        }
    }
}