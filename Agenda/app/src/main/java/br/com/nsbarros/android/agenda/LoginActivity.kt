package br.com.nsbarros.android.agenda

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.database.AppDatabase
import br.com.nsbarros.android.agenda.databinding.ActivityLoginBinding
import br.com.nsbarros.android.agenda.ui.activity.IDALUNO
import br.com.nsbarros.android.agenda.ui.activity.ListaAlunoActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private val daoUsuario by lazy{
        AppDatabase.instance(this).daoUsuario()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
        configuraBotaoEntrar()
    }

    private fun configuraBotaoEntrar() {
        binding.activityLoginBotaoEntrar.setOnClickListener {
            val usuario = binding.activityLoginUsuario.text.toString()
            val senha = binding.activityLoginSenha.text.toString()

            lifecycleScope.launch{
                val user = daoUsuario.autenticar(usuario, senha)
                user?.let {user ->
                    startActivity(Intent(this@LoginActivity, ListaAlunoActivity::class.java).also {
                        dataStore.edit { preferences->
                            preferences[USUARIOLOGADO] = user.id
                        }
                    })
                } ?: showErro()
            }
        }
    }

    private fun showErro() {
        Toast.makeText(this,
            "Usuário ou Senha invalido!",
            Toast.LENGTH_LONG)
            .show()
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            startActivity(
                Intent(this, FormularioCadastroUsuarioActivity::class.java)
            )
        }
    }

}