package br.com.nsbarros.android.agenda

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.lifecycleScope
import br.com.nsbarros.android.agenda.databinding.ActivityPerfilUsuarioBinding
import br.com.nsbarros.android.agenda.ui.activity.BaseActitivyUsuario
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PerfilUsuarioActivity : BaseActitivyUsuario() {

    private val binding by lazy {
        ActivityPerfilUsuarioBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bindView()
        configurarBotaoSair()
    }

    private fun bindView() {
        lifecycleScope.launch {
            usuario.collect {
                binding.activityPerfilUsuarioId.text = it?.nome
            }
        }
    }

    private fun configurarBotaoSair() {
        binding.activityPerfilUsuarioBotaoSairDoApp.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                lifecycleScope.launch {
                    deslogarUsuario()
                }
            }
        })
    }

}