package br.com.nsbarros.android.agenda.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.nsbarros.android.agenda.databinding.ActivityDetalhesAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import coil.load

class DetalhesAluno : AppCompatActivity() {

    companion object {
        val ALUNO = "DETALHES_EXTRA_ALUNO"
    }

    private val binding by lazy {
        ActivityDetalhesAlunoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent != null && intent.getSerializableExtra(ALUNO) != null){

            val aluno: Aluno = intent.getSerializableExtra(ALUNO) as Aluno

            binding.activityDetalhesAlunoButtonName.text = aluno.nome
            binding.activityDetalhesAlunoDescricaoAluno.text = "Nome: ${aluno.nome}, \n Email: ${aluno.email}\n Telefone: ${aluno.telefone}"
            binding.activityDetalhesAlunoImagemview.load(aluno.url){
                error(android.R.drawable.stat_notify_error)
                fallback(android.R.drawable.stat_notify_error)
                placeholder(android.R.drawable.btn_default)
            }

        }
    }
}