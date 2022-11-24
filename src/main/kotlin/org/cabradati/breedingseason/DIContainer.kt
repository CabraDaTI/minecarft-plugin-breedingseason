package org.cabradati.breedingseason

import org.bukkit.Server
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

data class DIContainer(
    val plugin: JavaPlugin,
    val server: Server,
    val config: FileConfiguration,
    val logger: Logger
) {

    fun log(mensagem: String) {
        logger.info(mensagem)
    }

}
