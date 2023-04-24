package br.com.nsbarros.android.agenda.dao.usuario

import br.com.nsbarros.android.agenda.model.Usuario
import kotlinx.coroutines.flow.Flow

interface UsuarioRepository {
    suspend fun insert(usuario: Usuario)
    suspend fun delete(id: String)
    fun findUsuarioByID(id: String): Flow<Usuario?>
    suspend fun findAllUsuarios(): List<Usuario>

    suspend fun autenticar(usuarioid: String, senhausuario: String) : Usuario?
}