package br.com.nsbarros.android.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.databinding.ActivityDetalhesAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import coil.load
import coil.request.Disposable
import kotlinx.coroutines.launch

class DetalhesAluno : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhesAlunoBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AlunoDao(this)
    }

    private var aluno: Aluno? = null
    private var idAluno: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       idAluno = intent.getLongExtra(IDALUNO, 0L)
    }

    override fun onResume() {
        super.onResume()
        buscarAluno()
    }

    private fun buscarAluno() {
        lifecycleScope.launch {
            aluno = dao.findById(idAluno)
            aluno?.let {
                tryLoading(it)
            } ?: finish()
        }
    }

    private fun tryLoading(aluno: Aluno): Disposable {
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
            }
            R.id.edit -> {
                editAluno()
            }
        }
        return true
    }

    private fun editAluno() {
        aluno?.let {
            Intent(this, FormularioAlunoActivity::class.java).apply {
                putExtra(IDALUNO, idAluno)
                startActivity(this)
            }
        }
    }

    private fun deleteAluno() {
        aluno?.let {
            lifecycleScope.launch {
                dao.delete(it)
            }
            showNotification()
            finish()
        }
    }

    private fun showNotification() {
        Toast.makeText(this, "${aluno?.nome}, deletado.", Toast.LENGTH_LONG).show()
    }

}