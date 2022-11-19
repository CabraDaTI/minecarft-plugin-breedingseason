package org.cabradati.breedingseason.events

import org.bukkit.block.Block
import org.bukkit.block.data.Ageable
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFertilizeEvent
import org.bukkit.event.block.BlockGrowEvent
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.models.EstacaoType

class ColheitaEvent(private val diContainer: DIContainer) : Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onColher(event: BlockGrowEvent) {
        if (isAgeable(event.block)) return

        if (diContainer.config.getString(App.ESTACAO_ATUAL) == EstacaoType.SECA.valor) {
            event.isCancelled = true
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    fun onFertilizar(event: BlockFertilizeEvent) {
        if (isAgeable(event.block)) return

        if (diContainer.config.getString(App.ESTACAO_ATUAL) == EstacaoType.SECA.valor) {
            event.isCancelled = true
        }
    }

    private fun isAgeable(block: Block): Boolean {
        return block is Ageable
    }

}