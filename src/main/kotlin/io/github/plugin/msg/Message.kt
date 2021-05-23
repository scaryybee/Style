package io.github.plugin.msg

import io.github.plugin.msg.command.Command
import io.github.plugin.msg.command.CommandTabCompleter
import io.github.plugin.msg.listener.ChatListener
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.annotations.NotNull
import java.io.File
import java.io.IOException

class Message: JavaPlugin() {

    var serverfile = File("plugins/MESSAGE/config.yml")
    var serverconfig: FileConfiguration? = null

    fun loadConfig() {
        serverconfig = YamlConfiguration.loadConfiguration(serverfile)
        try {
            if (!serverfile.exists()) {
                (serverconfig as @NotNull YamlConfiguration).save(serverfile)
            }
            (serverconfig as @NotNull YamlConfiguration).load(serverfile)
        } catch (localException: Exception) {
            localException.printStackTrace()
        }
    }

    override fun saveConfig() {
        try {
            serverconfig!!.save(serverfile)
        } catch (E: IOException) {
            E.printStackTrace()
        }
    }
    override fun onEnable() {
        server.pluginManager.also { spm ->
            spm.registerEvents(ChatListener(this), this)
        }
        getCommand("귓").also { command ->
            command?.setExecutor(Command(this))
        }
        getCommand("칭호").also { command ->
            command?.setExecutor(Command(this))
            command?.tabCompleter = CommandTabCompleter()
        }
        loadConfig()
    }

    fun returnColor(colorcode: Char): ChatColor? {
        when(colorcode) {
            '0' -> return ChatColor.BLACK
            '1' -> return ChatColor.DARK_BLUE
            '2' -> return ChatColor.DARK_GREEN
            '3' -> return ChatColor.DARK_AQUA
            '4' -> return ChatColor.DARK_RED
            '5' -> return ChatColor.DARK_PURPLE
            '6' -> return ChatColor.GOLD
            '7' -> return ChatColor.GRAY
            '8' -> return ChatColor.DARK_GRAY
            '9' -> return ChatColor.BLUE
            'a' -> return ChatColor.GREEN
            'b' -> return ChatColor.AQUA
            'c' -> return ChatColor.RED
            'd' -> return ChatColor.LIGHT_PURPLE
            'e' -> return ChatColor.YELLOW
            'f' -> return ChatColor.WHITE
            else -> return null
        }
    }
}