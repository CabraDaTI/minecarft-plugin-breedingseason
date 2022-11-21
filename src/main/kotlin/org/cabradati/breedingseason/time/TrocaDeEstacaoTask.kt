package org.cabradati.breedingseason.time

import net.kyori.adventure.text.Component
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.models.EstacaoType

class TrocaDeEstacaoTask(
    private val diContainer: DIContainer
) : Runnable {

    override fun run() {

        val config = diContainer.config

        val estacaoValor = config.getString(App.ESTACAO_ATUAL) ?: ""

        val proximaEstacao = when (EstacaoType.entryOf(estacaoValor)) {
            EstacaoType.NORMAL -> EstacaoType.INVERNO
            EstacaoType.INVERNO -> EstacaoType.NORMAL
        }

        val diasDaProximaEstacao = when (proximaEstacao) {
            EstacaoType.NORMAL -> config.getInt(App.ESTACAO_NORMAL_DIAS)
            EstacaoType.INVERNO -> config.getInt(App.ESTACAO_INVERNO_DIAS)
        }

        config.set(App.ESTACAO_ATUAL, proximaEstacao.valor)
        config.set(App.DIAS_RESTANTES_DA_ESTACAO, diasDaProximaEstacao)
        diContainer.plugin.saveConfig()

        diContainer.server.broadcast(Component.text("nova estação - ${proximaEstacao.valor}"))

    }

}