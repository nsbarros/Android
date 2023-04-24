package br.com.nsbarros.android.agenda.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Usuario(

    @PrimaryKey
    val id: String = "",
    val nome: String = "",
    val senha: String = ""
) : Serializable