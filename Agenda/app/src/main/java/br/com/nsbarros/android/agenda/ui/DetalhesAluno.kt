package br.com.nsbarros.android.agenda.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.database.AppDatabase
import br.com.nsbarros.android.agenda.database.dao.AlunoDaoI
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

    private lateinit var dao : AlunoDaoI
    private lateinit var aluno: Aluno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dao = AppDatabase.instance(this).daoAluno();

        if(intent != null && intent.getSerializableExtra(ALUNO) != null){

            aluno = intent.getSerializableExtra(ALUNO) as Aluno

            binding.activityDetalhesAlunoButtonName.text = aluno.nome
            binding.activityDetalhesAlunoDescricaoAluno.text = "Nome: ${aluno.nome}, \n Email: ${aluno.email}\n Telefone: ${aluno.telefone}"
            binding.activityDetalhesAlunoImagemview.load(aluno.url){
                error(android.R.drawable.stat_notify_error)
                fallback(android.R.drawable.stat_notify_error)
                placeholder(android.R.drawable.btn_default)
            }

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
        //AppDatabase.instance(this).daoAluno().up
    }

    private fun deleteAluno() {
        dao.delete(aluno)
        showNotification()
        finish()
    }

    private fun showNotification() {
        Toast.makeText(this, "${aluno.nome}, deletado.", Toast.LENGTH_LONG).show()
    }

}