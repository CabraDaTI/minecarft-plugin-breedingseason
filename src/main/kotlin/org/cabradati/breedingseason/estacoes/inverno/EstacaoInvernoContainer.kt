package org.cabradati.breedingseason.estacoes.inverno

import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.estacoes.inverno.events.AnimaisSpawnEvent
import org.cabradati.breedingseason.estacoes.inverno.events.ColheitaEvent
import org.cabradati.breedingseason.estacoes.inverno.schedulers.DanoScheduler

class EstacaoInvernoContainer(private val diContainer: DIContainer) {

    fun registerEvents() {

        diContainer.registerEvents(AnimaisSpawnEvent(diContainer))
        diContainer.registerEvents(ColheitaEvent(diContainer))

    }

    fun registerSchedulers() {

        diContainer.server.scheduler.runTaskTimer(
            diContainer.plugin,
            DanoScheduler(diContainer),
            1,
            100
        )

    }

}