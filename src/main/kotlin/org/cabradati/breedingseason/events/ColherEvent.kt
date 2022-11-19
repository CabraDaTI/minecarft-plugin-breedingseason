package org.cabradati.breedingseason.events

import net.kyori.adventure.text.Component
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerHarvestBlockEvent
import org.cabradati.breedingseason.DIContainer

class ColherEvent(private val diContainer: DIContainer) : Listener {

    fun onColher(event: PlayerHarvestBlockEvent) {
        diContainer.server.broadcast(
            Component.text(
                "type = " +
                        event.harvestedBlock.type.name
            )
        )
    }

}