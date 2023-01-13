package br.com.nsbarros.android.agenda.database.dao

import androidx.room.*
import br.com.nsbarros.android.agenda.model.Aluno

@Dao
interface AlunoDaoI {

    @Insert
    fun insert(aluno: Aluno)

    @Delete
    fun delete(aluno: Aluno)

    @Query("SELECT * FROM Aluno")
    fun getAll() : List<Aluno>

}