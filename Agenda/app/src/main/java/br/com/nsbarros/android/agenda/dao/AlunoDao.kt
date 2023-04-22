package br.com.nsbarros.android.agenda.dao

import android.content.Context
import br.com.nsbarros.android.agenda.database.AppDatabase
import br.com.nsbarros.android.agenda.model.Aluno

class AlunoDao(val context: Context) {

    private val db = AppDatabase.instance(context)

    suspend fun add(aluno: Aluno){
        db.daoAluno().insertOrReplace(aluno)
    }

    suspend fun findAll(): List<Aluno>{
        return db.daoAluno().getAll()
    }

    suspend fun findById(id: Long) : Aluno? {
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
