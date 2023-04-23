package br.com.nsbarros.android.agenda.dao.usuario

import android.content.Context
import br.com.nsbarros.android.agenda.database.AppDatabase
import br.com.nsbarros.android.agenda.model.Usuario

class UsuarioDao(context: Context) : UsuarioRepository {

    private val dao = AppDatabase.instance(context).daoUsuario()

    override suspend fun insert(usuario: Usuario) = dao.insert(usuario)


    override suspend fun delete(id: String) = dao.deleteByID(id)


    override suspend fun findUsuarioByID(id: String) = dao.findUsuarioById(id)


    override suspend fun findAllUsuarios() = dao.findAllUsuarios()

}