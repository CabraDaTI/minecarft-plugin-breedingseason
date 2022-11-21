package org.cabradati.breedingseason

import org.bukkit.entity.EntityType
import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.breedingseason.estacoes.inverno.EstacaoInvernoContainer
import org.cabradati.breedingseason.models.EstacaoType
import org.cabradati.breedingseason.time.MudancaDeDiaScheduler

class App : JavaPlugin() {

    companion object {
        const val ATIVAR_PLUGIN = "plugin.enabled"
        const val ATIVAR_DEBUG = "plugin.debug"
        const val PERIODO_DE_VALIDACAO = "config.ticks_per_day"

        const val DIAS_RESTANTES_DA_ESTACAO = "state.days_remaining"
        const val ESTACAO_ATUAL = "state.actual_seasson"

        const val ESTACAO_NORMAL_DIAS = "seasons.normal.days"

        const val ESTACAO_INVERNO_DIAS = "seasons.winter.days"
        const val ESTACAO_INVERNO_PREFIXO_SPAWN = "seasons.winter.spawn."
    }

    override fun onEnable() {

        config.addDefault(ATIVAR_PLUGIN, true)
        config.addDefault(ATIVAR_DEBUG, false)
        config.addDefault(PERIODO_DE_VALIDACAO, 24000)
        config.addDefault(ESTACAO_NORMAL_DIAS, 30)
        config.addDefault(ESTACAO_INVERNO_DIAS, 50)
        config.addDefault(DIAS_RESTANTES_DA_ESTACAO, 30)
        config.addDefault(ESTACAO_ATUAL, EstacaoType.NORMAL.valor)

        EntityType.values()
            .filter { entityType -> entityType != EntityType.UNKNOWN }
            .forEach { entityType ->
                config.addDefault(ESTACAO_INVERNO_PREFIXO_SPAWN + entityType, true)
            }

        config.options().copyDefaults(true)
        saveConfig()

        val diContainer = DIContainer(
            this,
            server,
            config
        )

        if (config.getBoolean(ATIVAR_PLUGIN)) {

            val diasRestantes = config.getInt(DIAS_RESTANTES_DA_ESTACAO) + 1
            config.set(DIAS_RESTANTES_DA_ESTACAO, diasRestantes)
            saveConfig()

            val inverno = EstacaoInvernoContainer(diContainer)
            inverno.registerEvents()
            inverno.registerSchedulers()

            server.scheduler.runTaskTimerAsynchronously(
                this,
                MudancaDeDiaScheduler(diContainer),
                1,
                config.getLong(PERIODO_DE_VALIDACAO)
            )
        }

        super.onEnable()
    }

}