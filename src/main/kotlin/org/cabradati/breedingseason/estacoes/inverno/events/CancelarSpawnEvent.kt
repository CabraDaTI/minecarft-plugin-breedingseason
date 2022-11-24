package org.cabradati.breedingseason.estacoes.inverno.events

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.models.EstacaoType
import org.cabradati.breedingseason.utils.extensions.SistemaEvent

class CancelarSpawnEvent(
    diContainer: DIContainer, private val entityType: EntityType
) : Listener, SistemaEvent<CreatureSpawnEvent> {

    private val parametroEstacaoAtual = diContainer.config.getString(App.ESTACAO_ATUAL)

    @EventHandler(priority = EventPriority.NORMAL)
    override fun on(event: CreatureSpawnEvent) {
        if (!(event.entity.type == entityType && parametroEstacaoAtual == EstacaoType.INVERNO.valor)) return
        event.isCancelled = true
    }

}