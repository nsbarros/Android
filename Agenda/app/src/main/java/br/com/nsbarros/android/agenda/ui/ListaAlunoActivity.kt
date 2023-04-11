package br.com.nsbarros.android.agenda.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.database.AppDatabase
import br.com.nsbarros.android.agenda.database.dao.AlunoDaoI
import br.com.nsbarros.android.agenda.databinding.ActivityListaAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.recyclerview.ListaAlunoAdapter

class ListaAlunoActivity : AppCompatActivity(){

    private val listaAlunoAdapter = ListaAlunoAdapter(this, alunos = emptyList(), whenClickItem =  {aluno -> irParaDetalhes(aluno)},
    whenLongClickRemove = {aluno -> deleteAluno(aluno)},
    whenLongClickEdit = {aluno -> editAluno(aluno)})

    private lateinit var dao : AlunoDaoI
    private lateinit var aluno : Aluno

    private val binding by lazy {
        ActivityListaAlunoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        configurarFab()
        configurarRecyclerView()
        dao = AppDatabase.instance(this).daoAluno();
    }

    private fun configurarFab() {
        val fab = binding.activityListaAlunoFab
        fab.setOnClickListener {
            irParaFormulario()
        }
    }

    private fun irParaFormulario() {
        val intentFormulario = Intent(this, FormularioAlunoActivity::class.java)
        startActivity(intentFormulario)
    }

    private fun irParaDetalhes(aluno: Aluno) {
        val intentGoDetails = Intent(this, DetalhesAluno::class.java)
        intentGoDetails.putExtra(DetalhesAluno.ALUNO, aluno)
        startActivity(intentGoDetails)
    }

    private fun configurarRecyclerView() {
        val recyclerView = binding.listaAlunoRecycleview
        recyclerView.adapter = listaAlunoAdapter

    }

    override fun onResume() {
        super.onResume()
        reload()
    }

    private fun reload() {
        listaAlunoAdapter.reload(AlunoDao(this).findAll())
    }

    private fun editAluno(mAluno: Aluno) {
        irParaDetalhes(mAluno)
    }

    private fun deleteAluno(mAluno: Aluno) {
        dao.delete(aluno = mAluno)
        reload()
    }
}