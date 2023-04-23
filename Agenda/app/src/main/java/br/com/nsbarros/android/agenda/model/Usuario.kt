package br.com.nsbarros.android.agenda.model

import androidx.room.Entity

@Entity
data class Usuario(
    val id: String,
    val nome: String,
    val senha: String
)