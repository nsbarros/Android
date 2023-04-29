package br.com.nsbarros.android.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.LoginActivity
import br.com.nsbarros.android.agenda.USUARIOLOGADO
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.dao.usuario.UsuarioDao
import br.com.nsbarros.android.agenda.dataStore
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.model.Usuario
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseActitivyUsuario : AppCompatActivity() {

    private val daoUsuario by lazy {
        UsuarioDao(this)
    }

    private val AlunoDao by lazy {
        AlunoDao(this)
    }

    private var _usuario: MutableStateFlow<Usuario?> = MutableStateFlow(null)

    protected var usuario: StateFlow<Usuario?> = _usuario

    private var _idAluno = 0L
    protected var idAluno = _idAluno

    private var _Aluno: MutableStateFlow<Aluno?> = MutableStateFlow(null)
    protected var aluno: StateFlow<Aluno?> = _Aluno

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            verificarUsuarioLogado()
        }
        intent.getLongExtra(IDALUNO, 0L).let {
            if(it > 0L){
               _idAluno = it
                lifecycleScope.launch{
                    buscarAluno()
                }
            }
        }
    }

    private suspend fun verificarUsuarioLogado() {
            dataStore.data.collect { preferences ->
                preferences[USUARIOLOGADO]?.let { userDateStore ->
                    buscarUsuario(userDateStore)
                } ?: goLoginActivity()
            }
    }

    private suspend fun buscarUsuario(idUsuario: String) {
           _usuario.value =  daoUsuario.findUsuarioByID(idUsuario).firstOrNull()
    }

    protected suspend fun deslogarUsuario() {
            dataStore.edit { preferences ->
                preferences.remove(USUARIOLOGADO)
            }
    }

     fun goLoginActivity() {
        Intent(this, LoginActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(this)
        }
        finish()
    }

    protected fun irParaFormulario(aluno: Aluno? = null) {
        alterarIdAluno(aluno)
        val intentFormulario = Intent(this, FormularioAlunoActivity::class.java)
        intentFormulario.putExtra(IDALUNO, _idAluno)
        startActivity(intentFormulario)
    }

    private fun alterarIdAluno(aluno: Aluno?) {
        aluno.let {
            _idAluno = it?.id ?: 0L
            idAluno = _idAluno
        }
    }

    protected fun irParaDetalhes(aluno: Aluno) {
        alterarIdAluno(aluno)
        val intentGoDetails = Intent(this, DetalhesAluno::class.java)
        intentGoDetails.putExtra(IDALUNO, _idAluno)
        startActivity(intentGoDetails)
    }

    protected fun editAluno(aluno: Aluno) {
        irParaFormulario(aluno)
    }

    private suspend fun buscarAluno(){
        AlunoDao.findById(_idAluno).collect{
            _Aluno.value = it
        }
    }


    protected fun deleteAluno(aluno: Aluno) {
        lifecycleScope.launch {
            AlunoDao.delete(aluno)
        }
    }

}