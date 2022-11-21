package org.cabradati.breedingseason.estacoes.inverno.events

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFertilizeEvent
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.models.EstacaoType
import org.cabradati.breedingseason.utils.extensions.isAgeable

class FertilizacaoEvent(private val diContainer: DIContainer) : Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onFertilizar(event: BlockFertilizeEvent) {
        if (event.block.isAgeable()) return

        if (diContainer.config.getString(App.ESTACAO_ATUAL) == EstacaoType.INVERNO.valor) {
            event.isCancelled = true
        }
    }

}