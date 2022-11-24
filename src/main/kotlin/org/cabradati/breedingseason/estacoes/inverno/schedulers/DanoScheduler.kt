package org.cabradati.breedingseason.estacoes.inverno.schedulers

import org.bukkit.Material
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.estacoes.inverno.tasks.DarDanoNoPlayerTask
import org.cabradati.breedingseason.models.EstacaoType

class DanoScheduler(
    private val diContainer: DIContainer
) : Runnable {

    private val parametroEstacaoAtual = diContainer.config.getString(App.ESTACAO_ATUAL)

    override fun run() {
        if (parametroEstacaoAtual != EstacaoType.INVERNO.valor) return

        diContainer.server.onlinePlayers
            .filter { player ->
                try {
                    player.equipment.chestplate?.type != Material.LEATHER_CHESTPLATE ||
                            player.equipment.leggings?.type != Material.LEATHER_LEGGINGS ||
                            player.equipment.boots?.type != Material.LEATHER_BOOTS
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