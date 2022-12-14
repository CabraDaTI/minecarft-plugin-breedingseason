package org.cabradati.breedingseason.estacoes.inverno.events

import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockGrowEvent
import org.cabradati.breedingseason.App
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.models.EstacaoType
import org.cabradati.breedingseason.utils.extensions.SistemaEvent
import org.cabradati.breedingseason.utils.extensions.isAgeable

class ColheitaEvent(
    diContainer: DIContainer
) : Listener, SistemaEvent<BlockGrowEvent> {

    private val parametroEstacaoAtual = diContainer.config.getString(App.ESTACAO_ATUAL)

    @EventHandler(priority = EventPriority.LOW)
    override fun on(event: BlockGrowEvent) {
        if (event.block.isAgeable() && parametroEstacaoAtual == EstacaoType.INVERNO.valor) {
            event.isCancelled = true
        }
    }

}