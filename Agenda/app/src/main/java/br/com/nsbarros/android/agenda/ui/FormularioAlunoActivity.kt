package br.com.nsbarros.android.agenda.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import br.com.nsbarros.android.agenda.R
import br.com.nsbarros.android.agenda.dao.AlunoDao
import br.com.nsbarros.android.agenda.model.Aluno

class FormularioAlunoActivity : AppCompatActivity(R.layout.activity_formulario_aluno) {

    private val dao = AlunoDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        val campoNome = findViewById<EditText>(R.id.activity_formulario_aluno_nome)
        val campoEmail = findViewById<EditText>(R.id.activity_formulario_aluno_email)
        val campoPhone = findViewById<EditText>(R.id.activity_formulario_aluno_phone)
        val btnSalvar = findViewById<Button>(R.id.activity_formulario_aluno_btn_salvar)

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