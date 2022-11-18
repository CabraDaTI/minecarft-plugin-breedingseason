package org.cabradati.breedingseason.tasks

import net.kyori.adventure.text.Component
import org.bukkit.Server
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.models.EstacaoType

class TrocaDeEstacaoTask(
    private val plugin: JavaPlugin,
    private val server: Server,
    private val config: FileConfiguration
) : Runnable {

    override fun run() {

        val estacaoValor = config.getString(App.ESTACAO_ATUAL) ?: ""

        val proximaEstacao = when (EstacaoType.entryOf(estacaoValor)) {
            EstacaoType.NORMAL -> EstacaoType.SECA
            EstacaoType.SECA -> EstacaoType.NORMAL
        }

        config.set(App.ESTACAO_ATUAL, proximaEstacao.valor)
        config.set(App.DIAS_RESTANTES_DA_ESTACAO, config.getInt(App.DIAS_POR_ESTACAO))
        plugin.saveConfig()

        server.broadcast(Component.text("nova estação - ${proximaEstacao.valor}"))

    }

}