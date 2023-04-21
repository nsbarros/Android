package br.com.nsbarros.android.agenda.ui.activity

import android.R
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.databinding.ActivityFormularioAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.dialog.DialogFormularioImagem
import coil.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioAlunoActivity : AppCompatActivity() {

    private var urlFoto: String = ""

    private var idAluno = 0L;

    private val binding by lazy {
        ActivityFormularioAlunoBinding.inflate(layoutInflater)
    }

    private val dao by lazy {
        AlunoDao(this)
    }

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindViews()
        idAluno = intent.getLongExtra(IDALUNO, 0L)
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

    override fun onResume() {
        super.onResume()
        scope.launch {
            dao.findById(idAluno)?.let {
                withContext(Dispatchers.Main){
                    tryLoading(it)
                }
            }
        }
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

            scope.launch {
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