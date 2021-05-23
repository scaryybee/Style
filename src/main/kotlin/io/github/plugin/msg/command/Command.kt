package io.github.plugin.msg.command

import io.github.plugin.msg.Message
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Command(private val plugin: Message): CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>): Boolean {
        when(cmd.name) {
            "귓" -> {
                if(args.size > 1) {
                    sender.sendMessage("${ChatColor.RED}보낼 플레이어 또는 보낼 메시지를 보내주세요!")
                    return false
                }
                val player = Bukkit.getPlayer(args[0])
                if(player == null) sender.sendMessage("${ChatColor.RED}존재하지 않는 플레이어입니다.")
                else if(player == sender) sender.sendMessage("${ChatColor.LIGHT_PURPLE}자기자신에게는 메시지를 보낼 수 없습니다.")
                else {
                    sender.sendMessage("${ChatColor.WHITE}[${ChatColor.GOLD}귓${ChatColor.WHITE}] ${sender.name} -> ${player.name} ${args[1]}")
                    player.sendMessage("${ChatColor.WHITE}[${ChatColor.GOLD}귓${ChatColor.WHITE}] ${sender.name} -> ${player.name} ${args[1]}")
                }
            }

            "칭호" -> {
                if(args.size < 3) {
                    sender.sendMessage("${ChatColor.RED}칭호를 줄 사람과 칭호를 입력해주세요!")
                    return false
                }
                when(args[0]) {
                    "설정" -> {
                        val player = Bukkit.getPlayer(args[1])
                        if(player == null) sender.sendMessage("${ChatColor.RED}존재하지 않는 플레이어입니다.")
                        var style = ""
                        val len = args.size - 1
                        for(i in 2..len) {
                            val sizeofargs = args[i].length - 1
                            for(j in 0..sizeofargs) {
                                if(args[2][j] == '&') {
                                    val color: ChatColor? = this.plugin.returnColor(args[2][j + 1])
                                    if(color == null) {
                                        style += args[2][j]
                                        continue
                                    }
                                    style += color
                                    continue
                                } else if(args[2][j - 1] == '&') {
                                    val color: ChatColor? = this.plugin.returnColor(args[2][j])
                                    if(color == null) {
                                        style += args[2][j]
                                    }
                                    continue
                                }
                                style += args[2][j]
                            }
                        }
                        this.plugin.serverconfig!!.set(player?.uniqueId.toString() + ".style", style)
                        this.plugin.saveConfig()
                    }
                }
            }

            "style" -> {
                if(args.size < 3) {
                    sender.sendMessage("${ChatColor.RED}칭호를 줄 사람과 칭호를 입력해주세요!")
                    return false
                }
                when(args[0]) {
                    "set" -> {
                        val player = Bukkit.getPlayer(args[1])
                        if(player == null) sender.sendMessage("${ChatColor.RED}존재하지 않는 플레이어입니다.")
                        var style = ""
                        val len = args.size - 1
                        for(i in 2..len) {
                            val sizeofargs = args[i].length - 1
                            for(j in 0..sizeofargs) {
                                if(args[2][j] == '&') {
                                    val color: ChatColor? = this.plugin.returnColor(args[2][j + 1])
                                    if(color == null) {
                                        style += args[2][j]
                                        continue
                                    }
                                    style += color
                                    continue
                                } else if(args[2][j - 1] == '&') {
                                    val color: ChatColor? = this.plugin.returnColor(args[2][j])
                                    if(color == null) {
                                        style += args[2][j]
                                    }
                                    continue
                                }
                                style += args[2][j]
                            }
                        }
                        this.plugin.serverconfig!!.set(player?.uniqueId.toString() + ".style", style)
                        this.plugin.saveConfig()
                    }
                }
            }
        }
        return false
    }
}