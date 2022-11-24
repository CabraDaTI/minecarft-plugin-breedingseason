package org.cabradati.breedingseason

import org.bukkit.plugin.java.JavaPlugin
import org.cabradati.breedingseason.estacoes.inverno.EstacaoInvernoContainer
import org.cabradati.breedingseason.models.EstacaoType
import org.cabradati.breedingseason.utils.extensions.SistemaContainer

class App : JavaPlugin() {

    companion object {
        const val ATIVAR_PLUGIN = "plugin.enabled"
        const val ATIVAR_DEBUG = "plugin.debug"
        const val ESTACAO_PREFIX = "estacoes."

        const val ESTACAO_ATUAL = "config.estacao_atual"
    }

    override fun onEnable() {

        config.addDefault(ATIVAR_PLUGIN, true)
        config.addDefault(ATIVAR_DEBUG, false)
        config.addDefault(ESTACAO_ATUAL, EstacaoType.NORMAL.valor)
        config.options().copyDefaults(true)

        val diContainer = DIContainer(
            this,
            server,
            config,
            logger
        )

        val listaContainer: List<SistemaContainer> = listOf(
            EstacaoInvernoContainer(diContainer)
        )

        listaContainer.forEach { config ->
            config.registerConfig()
        }

        if (config.getBoolean(ATIVAR_PLUGIN)) {

            listaContainer.forEach { config ->
                config.registerEvents()
                config.registerSchedulers()
            }

        }

        saveConfig()

        super.onEnable()
    }

}