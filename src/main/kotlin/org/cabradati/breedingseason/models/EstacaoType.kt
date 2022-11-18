package org.cabradati.breedingseason.models

enum class EstacaoType(val valor: String) {
    NORMAL("normal"),
    SECA("seca");

    companion object {
        fun entryOf(estacao: String): EstacaoType {
            return values().first { item ->
                item.valor == estacao
            }
        }
    }

}