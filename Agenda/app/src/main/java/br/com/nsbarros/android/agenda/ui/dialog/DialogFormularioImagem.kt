package br.com.nsbarros.android.agenda.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.nsbarros.android.agenda.databinding.DialogFotoFormularioBinding
import coil.load

class DialogFormularioImagem(val context: Context) {

    private var url = "";

    private val binding by lazy {
        DialogFotoFormularioBinding.inflate(LayoutInflater.from(context))
    }

    fun mostrar(callBack: (url: String) -> Unit) {
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { dialog, _ ->
                callBack(url)
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()

        binding.dialogFotoFormularioButtonCarregarfoto.setOnClickListener {
            this.url = binding.dialogFotoFormularioEdittextUrl.text.toString()
            binding.dialogFotoFormularioImageviewFoto.load(url) {
                error(android.R.drawable.stat_notify_error)
                fallback(android.R.drawable.stat_notify_error)
                placeholder(android.R.drawable.btn_default)
            }
        }
    }

}
