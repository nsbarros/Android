package br.com.nsbarros.android.agenda.dao

import android.content.Context
import br.com.nsbarros.android.agenda.database.AppDatabase
import br.com.nsbarros.android.agenda.model.Aluno

class AlunoDao(val context: Context) {

    private val db = AppDatabase.instance(context)

    fun add(aluno: Aluno){
        db.daoAluno().insertOrReplace(aluno)
    }

    fun findAll(): List<Aluno>{
        return db.daoAluno().getAll()
    }

    fun findById(id: Long) : Aluno? {
        return db.daoAluno().findByID(id)
    }
    fun delete(aluno: Aluno) {
        db.daoAluno().delete(aluno)
    }

}
