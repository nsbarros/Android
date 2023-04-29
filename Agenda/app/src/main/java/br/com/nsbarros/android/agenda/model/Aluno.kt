package br.com.nsbarros.android.agenda.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Aluno(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nome: String = "",
    val email: String = "",
    val telefone: String = "",
    var url: String? = "",
    var usuarioId: String? = null
) : Serializable
