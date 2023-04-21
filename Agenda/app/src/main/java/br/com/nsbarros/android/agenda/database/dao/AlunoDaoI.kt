package br.com.nsbarros.android.agenda.database.dao

import androidx.room.*
import br.com.nsbarros.android.agenda.model.Aluno

@Dao
interface AlunoDaoI {

    @Update
    fun update(aluno: Aluno)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplace(aluno: Aluno)

    @Delete
    fun delete(aluno: Aluno)

    @Query("SELECT * FROM Aluno")
    fun getAll() : List<Aluno>

    @Query("SELECT * FROM Aluno WHERE id =:id")
    fun findByID(id: Long): Aluno?

    @Query("SELECT * FROM Aluno order by nome asc")
    fun findAllOrderByNameAsc() : List<Aluno>
    @Query("SELECT * FROM Aluno order by nome desc")
    fun findAllOrderByNameDesc(): List<Aluno>

}