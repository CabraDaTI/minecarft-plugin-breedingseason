package org.cabradati.breedingseason.estacoes.inverno.events

import org.bukkit.entity.Animals
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.models.EstacaoType

class AnimaisSpawnEvent(private val diContainer: DIContainer) : Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    fun onAnimaisSpawnEvent(event: CreatureSpawnEvent) {

        if (event.entity !is Animals) return

        if (diContainer.config.getString(App.ESTACAO_ATUAL) != EstacaoType.INVERNO.valor) return

        if (!diContainer.config.getBoolean(App.ESTACAO_INVERNO_PREFIXO_SPAWN + event.entityType)) {
            event.isCancelled = true
        }

    }

}