package br.com.nsbarros.android.agenda.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.databinding.ActivityListaAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.recyclerview.ListaAlunoAdapter

class ListaAlunoActivity : AppCompatActivity() {

    private val listaAlunoAdapter = ListaAlunoAdapter(this, alunos = emptyList(), whenClickItem =  {aluno -> irParaDetalhes(aluno)})

    private val binding by lazy {
        ActivityListaAlunoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        configurarFab()
        configurarRecyclerView()
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
}