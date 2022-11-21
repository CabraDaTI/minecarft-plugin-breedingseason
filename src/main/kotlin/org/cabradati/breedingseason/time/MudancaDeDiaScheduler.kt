package org.cabradati.breedingseason.time

import net.kyori.adventure.text.Component
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer

class MudancaDeDiaScheduler(
    private val diContainer: DIContainer
) : Runnable {

    override fun run() {
        val config = diContainer.config
        val server = diContainer.server
        val plugin = diContainer.plugin

        val diasRestantes = config.getInt(App.DIAS_RESTANTES_DA_ESTACAO) - 1


        if (diasRestantes < 0) {
            server.scheduler.runTaskAsynchronously(plugin, TrocaDeEstacaoTask(diContainer))
        }

        if (diasRestantes == 0) {
            server.broadcast(Component.text("preparem-se para a mudança de estação"))
            config.set(App.DIAS_RESTANTES_DA_ESTACAO, diasRestantes)
            plugin.saveConfig()
        }

        if (diasRestantes >= 1) {
            server.broadcast(Component.text("restam $diasRestantes dias para mudança de estação"))
            config.set(App.DIAS_RESTANTES_DA_ESTACAO, diasRestantes)
            plugin.saveConfig()
        }

    }

}