package br.com.nsbarros.android.agenda.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.databinding.ActivityFormularioAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.dialog.DialogFormularioImagem
import coil.load

class FormularioAlunoActivity : AppCompatActivity() {

    lateinit var dao : AlunoDao

    private var urlFoto: String = ""

    private val binding by lazy {
        ActivityFormularioAlunoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        bindViews()
    }

    override fun onResume() {
        super.onResume()
        dao = AlunoDao(this);
    }

    private fun bindViews() {
        val campoNome = binding.activityFormularioAlunoNome
        val campoEmail = binding.activityFormularioAlunoEmail
        val campoPhone = binding.activityFormularioAlunoPhone
        val btnSalvar = binding.activityFormularioAlunoBtnSalvar

        binding.activityFormularioAlunoImageview.setOnClickListener {
            DialogFormularioImagem(this).mostrar{ urlImagem ->
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
            dao.add(oAluno)
            finish()
        }
    }

    private fun criarAluno(nome: String, email: String, phone: String, url: String): Aluno {
        return Aluno(0,
            nome,
            email,
            phone,
            url,
        )

    }
}