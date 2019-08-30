package com.suonk.socialmusician.model.requestDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.suonk.socialmusician.model.ModelDB.MusicianDB
import com.suonk.socialmusician.model.ModelDB.MusicianDetailsDB
import com.suonk.socialmusician.model.ModelDB.MusicianWithAllInformation

/**
 * Interface réunissent les différentes requêtes d'interaction avec la table musician detail
 * @author Kenzy Suon
 */
@Dao
interface MusiciansDao {
    /**
     * Récupère les [musicianList][MusicianWithAllInformation] trier par prénom A->Z
     * @return List&lt[MusicianWithAllInformation]&gt
     */
    @Query("SELECT * FROM musician_table ORDER BY first_name COLLATE NOCASE ASC")
    fun sortMusicianByFirstNameAZ(): List<MusicianWithAllInformation>

    /**
     * Récupère les [musicianList][MusicianDB] trier par nom A->Z
     * @return List&lt[MusicianDB]&gt
     */
    @Query("SELECT * FROM musician_table ORDER BY last_name COLLATE NOCASE ASC")
    fun sortMusicianByLastNameAZ(): List<MusicianWithAllInformation>

    /**
     * Récupère tout les [musicianList][MusicianDB] de la base de données
     * @return List&lt[MusicianDB]&gt
     */
    @Query("SELECT * FROM musician_table ORDER BY id ASC")
    fun getAllContacts(): List<MusicianDB>

    /**
     * Récupere les [musicianList][MusicianDB] trier par favoris
     * @return List&lt[MusicianDB]&gt
     */
    @Query("SELECT * FROM musician_table WHERE is_friend = 1 ORDER BY first_name COLLATE NOCASE ASC")
    fun getAllFriends(): List<MusicianDB>

    /**
     * Récupere un [musicianList][MusicianWithAllInformation] grâce à son id
     * @param id Int     Id de l'ami sélectionné
     * @return List&lt[MusicianWithAllInformation]&gt
     */
    @Query("SELECT * FROM musician_table WHERE id = :id AND is_friend = 1")
    fun getFriend(id: Int): MusicianWithAllInformation

    /**
     * Récupere un [musicianList][MusicianWithAllInformation] grâce à son id
     * @param id Int     Id de l'ami sélectionné
     * @return List&lt[MusicianWithAllInformation]&gt
     */
    @Query("SELECT * FROM musician_table WHERE id = :id")
    fun getMusician(id: Int): MusicianWithAllInformation

    /**
     * Récupere les [musicianList][MusicianDB] grâce à leurs nom ou prénom (pour la searchbar)
     * @param name String nom ou prénom du musician
     * @return List&lt[MusicianWithAllInformation]&gt
     */
    @Query("SELECT * FROM musician_table WHERE instr(lower(first_name||' '||last_name), lower(:name)) > 0")
    fun getMusicianByName(name: String): List<MusicianWithAllInformation>

    /**
     * Récupere les [musicianList][MusicianDB] grâce à leurs nom ou prénom (pour la searchbar)
     * @param name String nom ou prénom du musician
     * @return List&lt[MusicianWithAllInformation]&gt
     */
    @Query("SELECT * FROM musician_table WHERE is_friend = 1 AND instr(lower(first_name||' '||last_name), lower(:name)) > 0")
    fun getFriendByName(name: String): List<MusicianWithAllInformation>

    /**
     * Sauvegarde dans la base de données un [musicianList][MusicianDB]
     * @param MusicianDB MusicianDB     Objet [Contact][MusicianDB]
     * @return Long id du musician qu'on va sauvegarder
     */
    @Insert
    fun insert(MusicianDB: MusicianDB): Long?

    /**
     * Sauvegarde une liste de [ContactDetailDB] dans la base de données
     * @param musicianDetails List&lt[ContactDetailDB]&gt     Liste de musician avec toute leurs informations
     */
    @Insert
    fun insertDetails(musicianDetails: List<MusicianDetailsDB>)

    /**
     * Update un [musician][MusicianDB] grace à son id
     * @param id Int     Id du musician sélectionné
     * @param firstName String  Prénom du musician
     * @param lastName String   Nom du musician
     * @param profilePicture64 String   image du musician
     * @param priority Int  priorité du musician
     */
    @Query("UPDATE musician_table SET first_name = :firstName, last_name = :lastName, profile_picture_str = :profilePicture64, is_friend = :isFriend WHERE id = :id")
    fun updateContactById(id: Int, firstName: String, lastName: String, profilePicture64: String, isFriend: Int)

    /**
     * Update un [musician][MusicianDB] sans image de profil grace à son id
     * @param id Int     Id du musician sélectionné
     * @param firstName String  Prénom du musician
     * @param lastName String   Nom du musician
     */
    @Query("UPDATE musician_table SET first_name = :firstName, last_name = :lastName, is_friend = :isFriend WHERE id = :id")
    fun updateContactByIdWithoutPic(id: Int, firstName: String, lastName: String, isFriend: Int)

    /**
     * Update un [musician][MusicianDB] apres une synchronisation grace à son id
     * @param id Int     Id du musician sélectionné
     * @param firstName String  Prénom du musician
     * @param lastName String   Nom du musician
     */
    @Query("UPDATE musician_table SET first_name = :firstName, last_name = :lastName WHERE id = :id")
    fun updateContactByIdSync(id: Int, firstName: String, lastName: String)


    /**
     * UPDATE si un musicien est un ami ou pas
     * @param id Int    Id du musician sélectionné
     */
    @Query("UPDATE musician_table SET is_friend = 1 WHERE id = :id ")
    fun setIsFavorite(id: Int)

    /**
     * UPDATE si un musicien est un ami ou pas
     * @param id Int    Id du musician sélectionné
     */

    @Query("UPDATE musician_table SET is_friend = 0 WHERE id = :id ")
    fun setIsNotFavorite(id: Int)

    /**
     * UPDATE si le musician est un favori ou non
     * @param id Int    Id du musician sélectionné
     */

    @Query("DELETE FROM musician_table WHERE id = :id")
    fun deleteContactById(id: Int)

    /**
     * Supprime tout les [musicianList][MusicianDB] de la base de données
     */
    @Query("DELETE FROM musician_table")
    fun deleteAll()

    /**
     * Récupere les [musicianList][MusicianWithAllInformation] qui possèdent un mail
     * @return List&lt[MusicianWithAllInformation]&gt
     */
    @Query("SELECT musician_table.* FROM musician_table INNER JOIN musician_details_table ON musician_details_table.id_musician = musician_table.id WHERE type='mail'")
    fun getContactWithMail(): List<MusicianWithAllInformation>

    /**
     * Récupere les [musicianList][MusicianWithAllInformation] qui possèdent un numéro de téléphone
     * @return List&lt[MusicianWithAllInformation]&gt
     */
    @Query("SELECT musician_table.* FROM musician_table INNER JOIN musician_details_table ON musician_details_table.id_musician = musician_table.id WHERE type='phone'")
    fun getContactWithPhoneNumber(): List<MusicianWithAllInformation>

    /**
     * Récupere les [musicianList][MusicianDB]
     * @return List&lt[MusicianWithAllInformation]&gt
     */
    @Query("SELECT * FROM musician_table")
    fun getContactAllInfo(): List<MusicianWithAllInformation>
}