package br.com.nsbarros.android.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.databinding.ActivityListaAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.recyclerview.ListaAlunoAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListaAlunoActivity : AppCompatActivity() {

    private val listaAlunoAdapter = ListaAlunoAdapter(this,
        alunos = emptyList(),
        whenClickItem = { aluno -> irParaDetalhes(aluno) },
        whenLongClickRemove = { aluno -> deleteAluno(aluno) },
        whenLongClickEdit = { aluno -> editAluno(aluno) })

    private val binding by lazy {
        ActivityListaAlunoBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AlunoDao(this)
    }

    private val job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        configurarFab()
        configurarRecyclerView()
        lifecycleScope.launch(job) {
          dao.findAll().collect { listAlunos ->
                reload(listAlunos)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menulistaaluno, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nameasc -> {
                lifecycleScope.launch {
                    var list = dao.findAllNameAsc()
                    reload(list)
                }
            }
            R.id.namedesc -> {
                lifecycleScope.launch {
                    var list = dao.findAllNameDesc()
                    reload(list)
                }
            }
        }

        return true
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
        intentGoDetails.putExtra(IDALUNO, aluno.id)
        startActivity(intentGoDetails)
    }

    private fun configurarRecyclerView() {
        val recyclerView = binding.listaAlunoRecycleview
        recyclerView.adapter = listaAlunoAdapter

    }
    private fun reload(list: List<Aluno>) {
        listaAlunoAdapter.reload(list)
    }

    private fun editAluno(mAluno: Aluno) {
        irParaDetalhes(mAluno)
    }

    private fun deleteAluno(mAluno: Aluno) {
        lifecycleScope.launch {
            dao.delete(mAluno)
        }
    }
}