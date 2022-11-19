package org.cabradati.breedingseason

import org.bukkit.Material
import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.breedingseason.events.AnimaisSpawnEvent
import org.cabradati.breedingseason.events.ColheitaEvent
import org.cabradati.breedingseason.models.EstacaoType
import org.cabradati.breedingseason.schedulers.MudancaDeDiaRunnable

class App : JavaPlugin() {

    companion object {
        const val DIAS_POR_ESTACAO = "config.duration_in_days"
        const val PERIODO_DE_VALIDACAO = "config.ticks"
        const val DIAS_RESTANTES_DA_ESTACAO = "state.days_remaining"
        const val ESTACAO_ATUAL = "state.actual_seasson"
    }

    override fun onEnable() {

        config.addDefault(DIAS_POR_ESTACAO, 30)
        config.addDefault(PERIODO_DE_VALIDACAO, 24000)
        config.addDefault(DIAS_RESTANTES_DA_ESTACAO, 30)
        config.addDefault(ESTACAO_ATUAL, EstacaoType.NORMAL.valor)
        config.options().copyDefaults(true)
        saveConfig()

        val diContainer = DIContainer(
            this,
            server,
            config
        )

        server.scheduler.runTaskTimerAsynchronously(
            this,
            MudancaDeDiaRunnable(diContainer),
            1,
            config.getLong(PERIODO_DE_VALIDACAO)
        )

        server.pluginManager.registerEvents(
            AnimaisSpawnEvent(diContainer),
            this
        )

        server.pluginManager.registerEvents(
            ColheitaEvent(diContainer),
            this
        )
        Material.WHEAT

        super.onEnable()
    }

}