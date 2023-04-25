package br.com.nsbarros.android.agenda.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.databinding.ActivityDetalhesAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import coil.load
import coil.request.Disposable
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetalhesAluno : BaseActitivyUsuario() {

    private val binding by lazy {
        ActivityDetalhesAlunoBinding.inflate(layoutInflater)
    }

    private var mAluno: Aluno? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch {
            usuario.collect{
                it?.let {
                   aluno.collect{itAluno->
                       itAluno?.let{
                           bind(it)
                           mAluno = it
                       } ?: finish()
                   }
                }
            }

        }
    }

    private fun bind(aluno: Aluno): Disposable {
        binding.activityDetalhesAlunoButtonName.text = aluno.nome
        binding.activityDetalhesAlunoDescricaoAluno.text =
            "Nome: ${aluno.nome}, \n Email: ${aluno.email}\n Telefone: ${aluno.telefone}"
        return binding.activityDetalhesAlunoImagemview.load(aluno.url) {
            error(android.R.drawable.stat_notify_error)
            fallback(android.R.drawable.stat_notify_error)
            placeholder(android.R.drawable.btn_default)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.remove -> {
                deleteAluno()
                finish()
                showNotification()
            }
            R.id.edit -> {
                editAluno()
            }
        }
        return true
    }

    private fun showNotification() {
        Toast.makeText(this, "${mAluno?.nome}, deletado.", Toast.LENGTH_LONG).show()
    }

}