package br.com.nsbarros.android.agenda.database.dao.usuario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.nsbarros.android.agenda.model.Usuario

@Dao
interface UsuarioDaoI {

    @Insert
    suspend fun insert (usuario: Usuario)

    @Query("DELETE FROM USUARIO WHERE id = :id")
    suspend fun deleteByID(id: String)

    @Query("SELECT * FROM USUARIO WHERE id =:id")
    suspend fun findUsuarioById(id: String)

    @Query("SELECT * FROM USUARIO")
    suspend fun findAllUsuarios()
}