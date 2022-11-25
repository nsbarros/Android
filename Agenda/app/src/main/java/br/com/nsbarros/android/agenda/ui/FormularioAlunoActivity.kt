package br.com.nsbarros.android.agenda.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.databinding.ActivityFormularioAlunoBinding
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.ui.dialog.DialogFormularioImagem

class FormularioAlunoActivity : AppCompatActivity() {

    private val dao = AlunoDao()

    private val binding by lazy {
        ActivityFormularioAlunoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        bindViews()
    }

    private fun bindViews() {
        val campoNome = binding.activityFormularioAlunoNome
        val campoEmail = binding.activityFormularioAlunoEmail
        val campoPhone = binding.activityFormularioAlunoPhone
        val btnSalvar = binding.activityFormularioAlunoBtnSalvar

        binding.activityFormularioAlunoImageview.setOnClickListener {
            DialogFormularioImagem(this).mostrar();
        }

        adicionarAluno(btnSalvar, campoNome, campoEmail, campoPhone)

    }

    private fun adicionarAluno(
        btnSalvar: Button,
        campoNome: EditText,
        campoEmail: EditText,
        campoPhone: EditText
    ) {
        btnSalvar.setOnClickListener {
            val oAluno: Aluno = criarAluno(
                campoNome.text.toString(),
                campoEmail.text.toString(),
                campoPhone.text.toString()
            )
            dao.add(oAluno)
            finish()
        }
    }

    private fun criarAluno(nome: String, email: String, phone: String): Aluno {
        return Aluno(
            nome,
            email,
            phone
        )

    }
}