package br.com.nsbarros.android.agenda.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.databinding.ActivityListaAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.recyclerview.ListaAlunoAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListaAlunoActivity : BaseActitivyUsuario() {

    private val listaAlunoAdapter = ListaAlunoAdapter(this,
        alunos = emptyList(),
        whenClickItem = { aluno -> irParaDetalhes(aluno) },
        whenLongClickRemove = { aluno -> deleteAluno(aluno) },
        whenLongClickEdit = { aluno ->
            editAluno(aluno)
        })

    private val binding by lazy {
        ActivityListaAlunoBinding.inflate(layoutInflater)
    }

    private val daoAluno by lazy {
        AlunoDao(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        configurarFab()
        configurarRecyclerView()

        lifecycleScope.launch {
            usuario.collect() {
                it.let {
                    daoAluno.findAll().collect { listAlunos ->
                        reload(listAlunos)
                    }
                }
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
                    var list = daoAluno.findAllNameAsc()
                    reload(list)
                }
            }
            R.id.namedesc -> {
                lifecycleScope.launch {
                    var list = daoAluno.findAllNameDesc()
                    reload(list)
                }
            }
            R.id.deslogar -> {
                irParaPerfil()
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

    private fun configurarRecyclerView() {
        val recyclerView = binding.listaAlunoRecycleview
        recyclerView.adapter = listaAlunoAdapter

    }

    private fun reload(list: List<Aluno>) {
        listaAlunoAdapter.reload(list)
    }
}