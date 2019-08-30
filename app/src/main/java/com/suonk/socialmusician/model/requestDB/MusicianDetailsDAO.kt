package com.suonk.socialmusician.model.requestDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.suonk.socialmusician.model.ModelDB.MusicianDetailsDB

/**
 * Interface réunissent les différentes requêtes d'interaction avec la table contact detail
 * @author Kenzy Suon
 */
@Dao
interface MusicianDetailsDao {
    /**
     * Récupère un [contact detail][MusicianDetailsDB] qui possède un numéro de téléphone grâce à son id.
     * @param id Int    Id du contact sélectionné
     * @return [MusicianDetailsDB]
     */
    @Query("SELECT * FROM musician_details_table where type='phone' AND id_musician=:id")
    fun getPhoneNumberById(id: Int?): MusicianDetailsDB

    /**
     * Récupère un [contact detail][MusicianDetailsDB] qui possède une adresse mail grâce à son id.
     * @param id Int    Id du contact sélectionné
     * @return [MusicianDetailsDB]
     */
    @Query("SELECT * FROM musician_details_table where type='mail'AND id_musician=:id")
    fun getMailById(id: Int?): MusicianDetailsDB

    /**
     * Récupère tout les [contactList details][MusicianDetailsDB] de la table.
     * @return List&lt[MusicianDetailsDB]&gt
     */
    @Query("SELECT * FROM musician_details_table")
    fun getAllpropertiesEditContact(): List<MusicianDetailsDB>

    /**
     * Récupère tout les [contactList details][MusicianDetailsDB] que possède un contact mail grâce à son id.
     * @param contactID Int     Id du contact sélectionné
     * @return List&lt[MusicianDetailsDB]&gt
     */
    @Query("SELECT * FROM musician_details_table WHERE id_musician=:contactID")
    fun getDetailsForAContact(contactID: Int): List<MusicianDetailsDB>

    /**
     * Update un [contactList details][MusicianDetailsDB] grâce à son id.
     * @param id Int            Id du contact sélectionné
     * @param contactDetail     détail du contact (mail, numéro de tel, etc...)
     */
    @Query("UPDATE musician_details_table SET content = :contactDetail WHERE id = :id")
    fun updateContactDetailById(id: Int, contactDetail: String)

    /**
     * Ajoute un [contact detail][MusicianDetailsDB] dans la base de données.
     * @param MusicianDetailsDB MusicianDetailsDB    Objet [contact detail][MusicianDetailsDB]
     */
    @Insert
    fun insert(MusicianDetailsDB: MusicianDetailsDB)

    /**
     * Supprime un [contact detail][MusicianDetailsDB] dans la base de données.
     * @param id Int    id du detail
     */
    @Query("DELETE FROM musician_details_table WHERE id = :id")
    fun deleteDetailById(id: Int)

    /**
     * Supprime tout les [contact detail][MusicianDetailsDB] d'un contact.
     * @param id Int    id du contact
     */
    @Query("DELETE FROM musician_details_table WHERE id_musician = :id")
    fun deleteAllDetailsOfContact(id: Int)

}