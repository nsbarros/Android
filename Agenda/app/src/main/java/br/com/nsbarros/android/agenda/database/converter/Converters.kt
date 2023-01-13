package br.com.nsbarros.android.agenda.database.converter

import java.math.BigDecimal

class Converters {

    fun deDouble(valor : Double?) : BigDecimal{
        return valor?.let { BigDecimal(valor.toString()) } ?: BigDecimal.ZERO
    }

    fun bigDecimalToDouble(valor: BigDecimal?) : Double? {
        return valor?.let { valor.toDouble() }
    }
}