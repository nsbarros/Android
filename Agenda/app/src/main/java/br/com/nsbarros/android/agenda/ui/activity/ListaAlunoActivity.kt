package br.com.nsbarros.android.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.LoginActivity
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.USUARIOLOGADO
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.dao.usuario.UsuarioDao
import br.com.nsbarros.android.agenda.dataStore
import br.com.nsbarros.android.agenda.databinding.ActivityListaAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.recyclerview.ListaAlunoAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ListaAlunoActivity : AppCompatActivity() {

    private var idUsuario = ""

    private val listaAlunoAdapter = ListaAlunoAdapter(this,
        alunos = emptyList(),
        whenClickItem = { aluno -> irParaDetalhes(aluno) },
        whenLongClickRemove = { aluno -> deleteAluno(aluno) },
        whenLongClickEdit = { aluno -> editAluno(aluno) })

    private val binding by lazy {
        ActivityListaAlunoBinding.inflate(layoutInflater)
    }

    private val daoAluno by lazy {
        AlunoDao(this)
    }

    private val daoUsuario by lazy {
        UsuarioDao(this)
    }

    private val job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        configurarFab()
        configurarRecyclerView()

        verificarUsuarioLogado()
    }

    private fun verificarUsuarioLogado() {
        lifecycleScope.launch(job) {
            dataStore.data.collect { preferences ->
                preferences[USUARIOLOGADO]?.let { userDateStore ->
                    idUsuario = userDateStore
                    buscarUsuario()
                } ?: goLoginActivity()
            }
        }
    }

    private suspend fun buscarUsuario() {
        lifecycleScope.launch {
            daoUsuario.findUsuarioByID(idUsuario).firstOrNull()?.let {
                buscarAlunosUsuario()
            }
        }
    }

    private suspend fun buscarAlunosUsuario() {
        lifecycleScope.launch {
            daoAluno.findAll().collect { listAlunos ->
                reload(listAlunos)
            }
        }
    }

    private fun goLoginActivity() {
        Intent(this@ListaAlunoActivity, LoginActivity::class.java).apply {
            startActivity(this)
        }
        finish()
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
                lifecycleScope.launch {
                    dataStore.edit { preferences ->
                        preferences.remove(USUARIOLOGADO)
                    }
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
            daoAluno.delete(mAluno)
        }
    }
}