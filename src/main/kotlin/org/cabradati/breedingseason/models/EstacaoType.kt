package org.cabradati.breedingseason.models

enum class EstacaoType(val valor: String) {
    NORMAL("normal"),
    INVERNO("inverno");

    companion object {
        fun entryOf(estacao: String): EstacaoType {
            return values().first { item ->
                item.valor == estacao
            }
        }
    }

}