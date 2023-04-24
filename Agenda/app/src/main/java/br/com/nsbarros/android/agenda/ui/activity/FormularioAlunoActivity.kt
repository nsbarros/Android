package br.com.nsbarros.android.agenda.ui.activity

import android.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.USUARIOLOGADO
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.dataStore
import br.com.nsbarros.android.agenda.databinding.ActivityFormularioAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.dialog.DialogFormularioImagem
import coil.load
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FormularioAlunoActivity : AppCompatActivity() {

    private var urlFoto: String = ""

    private var idAluno = 0L;

    private val binding by lazy {
        ActivityFormularioAlunoBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AlunoDao(this)
    }

    private var IDUSUARIO = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindViews()
        idAluno = intent.getLongExtra(IDALUNO, 0L)

        lifecycleScope.launch {
            dataStore.data.collect{preferences ->
                preferences[USUARIOLOGADO]?.let {userLogado ->
                    IDUSUARIO = userLogado
                }
            }
            dao.findById(idAluno).collect { mAluno ->
                mAluno?.let {
                    tryLoading(it)
                }
            }
        }
    }

    private fun tryLoading(mAluno: Aluno) {
        urlFoto = mAluno.url.toString()
        binding.activityFormularioAlunoImageview.load(mAluno.url) {
            error(R.drawable.stat_notify_error)
            fallback(R.drawable.stat_notify_error)
            placeholder(R.drawable.btn_default)
        }
        binding.activityFormularioAlunoNome.setText(mAluno.nome)
        binding.activityFormularioAlunoEmail.setText(mAluno.email)
        binding.activityFormularioAlunoPhone.setText(mAluno.telefone)
    }

    private fun bindViews() {
        val campoNome = binding.activityFormularioAlunoNome
        val campoEmail = binding.activityFormularioAlunoEmail
        val campoPhone = binding.activityFormularioAlunoPhone
        val btnSalvar = binding.activityFormularioAlunoBtnSalvar

        binding.activityFormularioAlunoImageview.setOnClickListener {
            DialogFormularioImagem(this).mostrar { urlImagem ->
                urlFoto = urlImagem
                binding.activityFormularioAlunoImageview.load(urlFoto)
            }
        }

        adicionarAluno(btnSalvar, campoNome, campoEmail, campoPhone, urlFoto)

    }

    private fun adicionarAluno(
        btnSalvar: Button,
        campoNome: EditText,
        campoEmail: EditText,
        campoPhone: EditText,
        campoUrl: String
    ) {
        btnSalvar.setOnClickListener {
            val oAluno: Aluno = criarAluno(
                campoNome.text.toString(),
                campoEmail.text.toString(),
                campoPhone.text.toString(),
                urlFoto,
            )

            val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
                MainScope().launch {
                    Toast.makeText(
                        this@FormularioAlunoActivity,
                        "Erro: $throwable",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }

            lifecycleScope.launch(handler) {
                if (oAluno.nome.isBlank() || oAluno.email.isBlank()) {
                    throw Exception(getString(br.com.nsbarros.android.agenda.R.string.activity_formulario_erro_name_email))
                }
                dao.add(oAluno)
                finish()
            }

        }
    }

    private fun criarAluno(nome: String, email: String, phone: String, url: String): Aluno {
        return Aluno(
            idAluno.toLong(),
            nome,
            email,
            phone,
            url,
        )

    }
}