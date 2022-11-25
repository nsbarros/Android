package br.com.nsbarros.android.agenda.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog
import br.com.nsbarros.android.agenda.R

class DialogFormularioImagem(val context: Context) {

    fun mostrar() {
        AlertDialog.Builder(context)
            .setView(R.layout.dialog_foto_formulario)
            .setPositiveButton("Confirmar") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}
