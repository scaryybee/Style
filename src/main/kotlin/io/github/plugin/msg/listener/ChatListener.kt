package io.github.plugin.msg.listener

import io.github.plugin.msg.Message
import io.papermc.paper.event.player.ChatEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class ChatListener(private val plugin: Message): Listener {
    @EventHandler
    fun chatEvent(event: ChatEvent) {
        val sender = event.player
        if(this.plugin.serverconfig!!.getString(sender.uniqueId.toString()) == null) return
        event.isCancelled = true
        Bukkit.getServer().sendMessage(Component.text("${this.plugin.serverconfig!!.getString(sender.uniqueId.toString() + ".style").toString()} ${(event.message() as net.kyori.adventure.text.TextComponent).content()}"))
    }
}