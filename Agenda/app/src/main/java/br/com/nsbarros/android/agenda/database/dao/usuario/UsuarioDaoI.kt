package br.com.nsbarros.android.agenda.database.dao.usuario

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.nsbarros.android.agenda.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDaoI {

    @Insert
    suspend fun insert(usuario: Usuario)

    @Query("DELETE FROM USUARIO WHERE id = :id")
    suspend fun deleteByID(id: String)

    @Query("SELECT * FROM USUARIO WHERE id =:id")
    fun findUsuarioById(id: String): Flow<Usuario?>

    @Query("SELECT * FROM USUARIO")
    suspend fun findAllUsuarios(): List<Usuario>

    @Query(
        """
        SELECT * 
        FROM USUARIO 
        where id = :usuarioid
        and senha = :senhausuario"""
    )
    suspend fun autenticar(usuarioid: String, senhausuario: String): Usuario?
}