package br.com.nsbarros.android.agenda.dao.usuario

import br.com.nsbarros.android.agenda.model.Usuario

interface UsuarioRepository {
    suspend fun insert(usuario: Usuario)
    suspend fun delete(id: String)
    suspend fun findUsuarioByID(id: String)
    suspend fun findAllUsuarios()
}