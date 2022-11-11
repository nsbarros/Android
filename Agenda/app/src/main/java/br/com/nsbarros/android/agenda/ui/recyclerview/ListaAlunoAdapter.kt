package br.com.nsbarros.android.agenda.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.model.Aluno

class ListaAlunoAdapter(
    private val context: Context,
    alunos: List<Aluno>
) :
    RecyclerView.Adapter<ListaAlunoAdapter.ViewHolder>() {

    private val alunos = alunos.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(aluno: Aluno) {
            val campoNome = itemView.findViewById<TextView>(R.id.item_aluno_adapter_nome)
            val campoEmail = itemView.findViewById<TextView>(R.id.item_aluno_adapter_email)
            val campoTelefone = itemView.findViewById<TextView>(R.id.item_aluno_adapter_telefone)

            campoNome.text = aluno.nome
            campoEmail.text = aluno.email
            campoTelefone.text = aluno.telefone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_aluno_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(alunos[position])
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

}
