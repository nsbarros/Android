package br.com.nsbarros.android.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import br.com.nsbarros.android.agenda.databinding.ActivityLoginBinding
import br.com.nsbarros.android.agenda.ui.activity.FormularioAlunoActivity
import br.com.nsbarros.android.agenda.ui.activity.ListaAlunoActivity

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
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
            Log.i("LoginActivity", "onCreate: $usuario - $senha")
            startActivity(Intent(this, ListaAlunoActivity::class.java))
        }
    }

    private fun configuraBotaoCadastrar() {
        binding.activityLoginBotaoCadastrar.setOnClickListener {
            startActivity(Intent(this, FormularioCadastroUsuarioActivity::class.java))
        }
    }

}