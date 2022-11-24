package org.cabradati.breedingseason.estacoes.inverno.tasks

import org.bukkit.entity.Player

class DarDanoNoPlayerTask(
    private val player: Player,
    private val quantidade: Double
) : Runnable {

    override fun run() {
        player.damage(quantidade)
    }

}