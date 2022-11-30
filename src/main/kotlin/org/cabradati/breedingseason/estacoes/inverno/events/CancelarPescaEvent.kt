package org.cabradati.breedingseason.estacoes.inverno.events

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.models.EstacaoType
import org.cabradati.breedingseason.utils.extensions.SistemaEvent

class CancelarPescaEvent(
    diContainer: DIContainer
) : Listener, SistemaEvent<PlayerFishEvent> {

    private val parametroEstacaoAtual = diContainer.config.getString(App.ESTACAO_ATUAL)

    @EventHandler(priority = EventPriority.NORMAL)
    override fun on(event: PlayerFishEvent) {
        if (parametroEstacaoAtual != EstacaoType.INVERNO.valor) return
        if (event.state == PlayerFishEvent.State.BITE) {
            event.isCancelled = true
        }
    }

}