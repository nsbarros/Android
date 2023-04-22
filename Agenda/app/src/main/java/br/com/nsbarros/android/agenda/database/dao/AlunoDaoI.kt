package br.com.nsbarros.android.agenda.database.dao

import androidx.room.*
import br.com.nsbarros.android.agenda.model.Aluno

@Dao
interface AlunoDaoI {

    @Update
    suspend fun update(aluno: Aluno)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(aluno: Aluno)

    @Delete
    suspend fun delete(aluno: Aluno)

    @Query("SELECT * FROM Aluno")
    suspend fun getAll() : List<Aluno>

    @Query("SELECT * FROM Aluno WHERE id =:id")
    suspend fun findByID(id: Long): Aluno?

    @Query("SELECT * FROM Aluno order by nome asc")
    suspend fun findAllOrderByNameAsc() : List<Aluno>
    @Query("SELECT * FROM Aluno order by nome desc")
    suspend fun findAllOrderByNameDesc(): List<Aluno>

}