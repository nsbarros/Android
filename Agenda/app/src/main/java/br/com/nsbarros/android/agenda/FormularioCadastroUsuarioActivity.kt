package br.com.nsbarros.android.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.database.AppDatabase
import br.com.nsbarros.android.agenda.databinding.ActivityFormularioCadastroUsuarioBinding
import br.com.nsbarros.android.agenda.model.Usuario
import kotlinx.coroutines.launch

class FormularioCadastroUsuarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroUsuarioBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AppDatabase.instance(this).daoUsuario()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }

    private fun configuraBotaoCadastrar() {
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener {
            val novoUsuario = criaUsuario()
            Log.i("CadastroUsuario", "onCreate: $novoUsuario")
            lifecycleScope.launch{
                try {
                    dao.insert(novoUsuario)
                } catch (e: Exception) {
                    Toast.makeText(this@FormularioCadastroUsuarioActivity,
                    "Falha ao cadastrar usuário",
                    Toast.LENGTH_LONG).show()
                }
            }
            finish()
        }
    }

    private fun criaUsuario(): Usuario {
        val usuario = binding.activityFormularioCadastroUsuario.text.toString()
        val nome = binding.activityFormularioCadastroNome.text.toString()
        val senha = binding.activityFormularioCadastroSenha.text.toString()
        return Usuario(usuario, nome, senha)
    }
}