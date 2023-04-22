package br.com.nsbarros.android.agenda.database.dao

import androidx.room.*
import br.com.nsbarros.android.agenda.model.Aluno
import kotlinx.coroutines.flow.Flow

@Dao
interface AlunoDaoI {

    @Update
    suspend fun update(aluno: Aluno)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(aluno: Aluno)

    @Delete
    suspend fun delete(aluno: Aluno)

    @Query("SELECT * FROM Aluno")
     fun getAll() : Flow<List<Aluno>>

    @Query("SELECT * FROM Aluno WHERE id =:id")
     fun findByID(id: Long): Flow<Aluno?>

    @Query("SELECT * FROM Aluno order by nome asc")
    suspend fun findAllOrderByNameAsc() : List<Aluno>
    @Query("SELECT * FROM Aluno order by nome desc")
    suspend fun findAllOrderByNameDesc(): List<Aluno>

}