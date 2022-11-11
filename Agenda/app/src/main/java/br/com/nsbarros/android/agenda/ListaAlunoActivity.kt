package br.com.nsbarros.android.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.recyclerview.ListaAlunoAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaAlunoActivity : AppCompatActivity(R.layout.activity_lista_aluno) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = findViewById<RecyclerView>(R.id.lista_aluno_recycleview)
        val fab = findViewById<FloatingActionButton>(R.id.activity_lista_aluno_fab)

        fab.setOnClickListener {
            Toast.makeText(this, "FAB", Toast.LENGTH_LONG).show()
        }

        val lista = ArrayList<Aluno>()
        lista.add(Aluno("Nathan", "NATHAN@GMAIL.COM", "62994083866"))

        recyclerView.adapter = ListaAlunoAdapter(this, lista)
    }
}