package br.com.nsbarros.android.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.ui.recyclerview.FormularioAlunoActivity
import br.com.nsbarros.android.agenda.ui.recyclerview.ListaAlunoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaAlunoActivity : AppCompatActivity(R.layout.activity_lista_aluno) {

    private val alunoDao = AlunoDao();
    private val listaAlunoAdapter = ListaAlunoAdapter(this, alunoDao.findAll())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.lista_aluno_recycleview)
        val fab = findViewById<FloatingActionButton>(R.id.activity_lista_aluno_fab)

        recyclerView.adapter = listaAlunoAdapter

        fab.setOnClickListener {
            val intentFormulario = Intent (this, FormularioAlunoActivity::class.java)
            startActivity(intentFormulario)
        }

    }

    override fun onResume() {
        super.onResume()
        listaAlunoAdapter.reload(alunoDao.findAll())
        listaAlunoAdapter.notifyDataSetChanged();
    }
}