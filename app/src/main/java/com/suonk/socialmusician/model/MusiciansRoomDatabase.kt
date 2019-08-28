package com.suonk.socialmusician.model

import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import android.content.Context
import com.example.knocker.model.ModelDB.*
import com.example.knocker.model.requestDB.*

/**
 * La Classe qui permet de créer la base de données et de la garder à jour
 * @author Ryan Granet
 */
@Database(entities = [ContactDB::class, NotificationDB::class, GroupDB::class, ContactDetailDB::class, LinkContactGroup::class], version = 12)
abstract class ContactsRoomDatabase : RoomDatabase() {
    abstract fun musiciansDao(): ContactsDao
    abstract fun musiciansDetailsDao(): ContactDetailsDao

    companion object {
        private var INSTANCE: ContactsRoomDatabase? = null

        //creation de la base de données
        fun getDatabase(context: Context): ContactsRoomDatabase? {
            if (INSTANCE != null) {
                return INSTANCE
            }
            synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactsRoomDatabase::class.java,
                        "Contact_database")
                        .addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                return INSTANCE
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE contacts_table " + " ADD COLUMN mail TEXT DEFAULT '' NOT NULL")
            }
        }
    }
}