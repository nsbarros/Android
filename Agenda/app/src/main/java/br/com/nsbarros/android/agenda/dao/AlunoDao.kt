package br.com.nsbarros.android.agenda.dao

import android.content.Context
import br.com.nsbarros.android.agenda.database.AppDatabase
import br.com.nsbarros.android.agenda.model.Aluno
import kotlinx.coroutines.flow.Flow

class AlunoDao(val context: Context) {

    private val db = AppDatabase.instance(context)

    suspend fun add(aluno: Aluno){
        db.daoAluno().insertOrReplace(aluno)
    }

     fun findAll(): Flow<List<Aluno>> {
        return db.daoAluno().getAll()
    }

     fun findById(id: Long) : Flow<Aluno?> {
        return db.daoAluno().findByID(id)
    }
    suspend fun delete(aluno: Aluno) {
        db.daoAluno().delete(aluno)
    }

    suspend fun findAllNameDesc(): List<Aluno> {
        return db.daoAluno().findAllOrderByNameDesc()
    }

    suspend fun findAllNameAsc(): List<Aluno> {
        return db.daoAluno().findAllOrderByNameAsc()
    }

}
