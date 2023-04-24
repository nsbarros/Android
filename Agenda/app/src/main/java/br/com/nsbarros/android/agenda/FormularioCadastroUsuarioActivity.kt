package br.com.nsbarros.android.agenda

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.dao.usuario.UsuarioDao
import br.com.nsbarros.android.agenda.databinding.ActivityFormularioCadastroUsuarioBinding
import br.com.nsbarros.android.agenda.model.Usuario
import kotlinx.coroutines.launch

class FormularioCadastroUsuarioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioCadastroUsuarioBinding.inflate(layoutInflater)
    }

    private val daoUsuario by lazy {
        UsuarioDao(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoCadastrar()
    }

    private fun configuraBotaoCadastrar() {
        binding.activityFormularioCadastroBotaoCadastrar.setOnClickListener {
            cadastrar()
            finish()
        }
    }

    private fun cadastrar() {
        criaUsuario().let{usuario ->
            lifecycleScope.launch {
                try {
                    daoUsuario.insert(usuario)
                } catch (e: Exception) {
                    showErro()
                }
            }
        }
    }

    private fun showErro() {
        Toast.makeText(
            this@FormularioCadastroUsuarioActivity,
            "Falha ao cadastrar usuário",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun criaUsuario(): Usuario {
        val usuario = binding.activityFormularioCadastroUsuario.text.toString()
        val nome = binding.activityFormularioCadastroNome.text.toString()
        val senha = binding.activityFormularioCadastroSenha.text.toString()
        return Usuario(usuario, nome, senha)
    }
}