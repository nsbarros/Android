package br.com.nsbarros.android.agenda.dao

import br.com.nsbarros.android.agenda.model.Aluno

class AlunoDao {

    fun add(aluno: Aluno){
        list.add(aluno)
    }

    fun findAll(): List<Aluno>{
        return list.toList()
    }

    companion object {
        val list = mutableListOf<Aluno>()
    }

}
