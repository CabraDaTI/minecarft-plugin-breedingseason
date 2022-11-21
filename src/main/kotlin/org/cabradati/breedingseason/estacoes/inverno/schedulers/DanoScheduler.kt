package org.cabradati.breedingseason.estacoes.inverno.schedulers

import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.estacoes.inverno.tasks.DarDanoNoPlayerTask
import org.cabradati.breedingseason.models.EstacaoType

class DanoScheduler(
    private val diContainer: DIContainer
) : Runnable {

    override fun run() {

        if (diContainer.config.getString(App.ESTACAO_ATUAL) != EstacaoType.INVERNO.valor) return

        diContainer.server.onlinePlayers
            .filter { player ->
                try {
                    player.equipment.boots.amount == 0 ||
                            player.equipment.leggings.amount == 0 ||
                            player.equipment.chestplate.amount == 0
                } catch (e: Exception) {
                    true
                }
            }
            .forEach { player ->
                diContainer.server.scheduler.runTask(
                    diContainer.plugin,
                    DarDanoNoPlayerTask(player, 1.0)
                )
            }
    }

}