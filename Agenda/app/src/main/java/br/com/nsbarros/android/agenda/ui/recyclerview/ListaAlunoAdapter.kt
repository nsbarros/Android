package br.com.nsbarros.android.agenda.ui.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nsbarros.android.agenda.databinding.ItemAlunoAdapterBinding
import br.com.nsbarros.android.agenda.model.Aluno
import coil.load

class ListaAlunoAdapter(
    private val context: Context,
    alunos: List<Aluno>
) :
    RecyclerView.Adapter<ListaAlunoAdapter.ViewHolder>() {

    private val alunos = alunos.toMutableList()

    class ViewHolder(binding: ItemAlunoAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

        private val campoNome = binding.itemAlunoAdapterNome
        private val campoEmail = binding.itemAlunoAdapterEmail
        private val campoTelefone = binding.itemAlunoAdapterTelefone
        private val imagemViewFoto = binding.itemAlunoImagem

        fun bind(aluno: Aluno) {

            campoNome.text = aluno.nome
            campoEmail.text = aluno.email
            campoTelefone.text = aluno.telefone
            imagemViewFoto.load(aluno.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlunoAdapterBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(alunos[position])
    }

    override fun getItemCount(): Int {
        return alunos.size
    }

    fun reload(listAlunos: List<Aluno>) {
        alunos.clear();
        alunos.addAll(listAlunos)
        notifyDataSetChanged()
    }

}
