package org.cabradati.breedingseason

import org.bukkit.Server
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

data class DIContainer(
    val plugin: JavaPlugin,
    val server: Server,
    val config: FileConfiguration
) {

    fun registerEvents(listener: Listener) {
        server.pluginManager.registerEvents(listener, plugin)
    }

}
