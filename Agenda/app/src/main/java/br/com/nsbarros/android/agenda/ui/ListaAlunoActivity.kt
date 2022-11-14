package br.com.nsbarros.android.agenda.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.ui.recyclerview.ListaAlunoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaAlunoActivity : AppCompatActivity(R.layout.activity_lista_aluno) {

    private val alunoDao = AlunoDao();
    private val listaAlunoAdapter = ListaAlunoAdapter(this, alunoDao.findAll())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configurarFab()
        configurarRecyclerView()
    }

    private fun configurarFab() {
        val fab = findViewById<FloatingActionButton>(R.id.activity_lista_aluno_fab)
        fab.setOnClickListener {
            irParaFormulario()
        }
    }

    private fun irParaFormulario() {
        val intentFormulario = Intent(this, FormularioAlunoActivity::class.java)
        startActivity(intentFormulario)
    }

    private fun configurarRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.lista_aluno_recycleview)
        recyclerView.adapter = listaAlunoAdapter
    }

    override fun onResume() {
        super.onResume()
        reload()
    }

    private fun reload() {
        listaAlunoAdapter.reload(alunoDao.findAll())
        listaAlunoAdapter.notifyDataSetChanged();
    }
}