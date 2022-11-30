package org.cabradati.breedingseason.estacoes.inverno

import org.bukkit.entity.EntityType
import org.cabradati.breedingseason.DIContainer
import org.cabradati.breedingseason.estacoes.inverno.events.CancelarPescaEvent
import org.cabradati.breedingseason.estacoes.inverno.events.CancelarSpawnEvent
import org.cabradati.breedingseason.estacoes.inverno.events.ColheitaEvent
import org.cabradati.breedingseason.estacoes.inverno.events.FertilizacaoEvent
import org.cabradati.breedingseason.estacoes.inverno.schedulers.DanoScheduler
import org.cabradati.breedingseason.utils.extensions.SistemaContainer

class EstacaoInvernoContainer(private val diContainer: DIContainer) : SistemaContainer {

    private val parametroAtivarEstacao = diContainer.config.getBoolean(EstacaoInvernoConsts.ATIVAR_ESTACAO)

    override fun registerConfig() {

        val config = diContainer.config
        val plugin = diContainer.plugin

        diContainer.log("estacao - inverno - registrando configurações")
        config.addDefault(EstacaoInvernoConsts.ATIVAR_ESTACAO, true)

        EntityType.values()
            .filter { entityType -> entityType != EntityType.UNKNOWN }
            .sortedBy { entityType -> entityType.name }
            .forEach { entityType ->
                config.addDefault(
                    EstacaoInvernoConsts.ATIVAR_SPAWN_ANIMAIS_PREFIXO + entityType,
                    true
                )
            }

        config.options().copyDefaults(true)
        plugin.saveConfig()

    }

    override fun registerEvents() {

        val config = diContainer.config
        val plugin = diContainer.plugin
        val server = diContainer.server

        if (parametroAtivarEstacao) {

            diContainer.log("estacao - inverno - registrando eventos")
            server.pluginManager.registerEvents(
                ColheitaEvent(diContainer),
                plugin
            )

            server.pluginManager.registerEvents(
                FertilizacaoEvent(diContainer),
                plugin
            )

            server.pluginManager.registerEvents(
                CancelarPescaEvent(diContainer),
                plugin
            )

            EntityType.values()
                .filter { entityType -> entityType != EntityType.UNKNOWN }
                .filter { entityType -> !config.getBoolean(EstacaoInvernoConsts.ATIVAR_SPAWN_ANIMAIS_PREFIXO + entityType) }
                .forEach { entityType ->
                    server.pluginManager.registerEvents(
                        CancelarSpawnEvent(diContainer, entityType),
                        plugin
                    )
                }


        } else {
            diContainer.log("estacao - inverno - eventos estão desabilitados")
        }

    }

    override fun registerSchedulers() {

        val plugin = diContainer.plugin
        val server = diContainer.server

        if (parametroAtivarEstacao) {

            diContainer.log("estacao - inverno - registrando schedulers")
            server.scheduler.runTaskTimerAsynchronously(
                plugin,
                DanoScheduler(diContainer),
                1,
                100
            )

        } else {
            diContainer.log("estacao - inverno - schedulers estão desabilitados")
        }

    }

}