package me.guiihsilva.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.guiihsilva.Main;

public class trocarmotd implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("trocarmotd")){
			if (!sender.hasPermission("motd.trocar")){
				sender.sendMessage("§cSem permissão!");
				return true;
			}
			String msg = "";
			for (int i = 0; i < args.length; i++) {
				msg = msg + args[i] + " ";
			}
			Main.instance.getConfig().set("msgs.secondline", msg.replaceAll("&", "§"));
			Main.instance.getConfig().options().copyDefaults(true);
			Main.instance.saveConfig();
			sender.sendMessage("§b* §7Você alterou o motd para: §e" + msg.replaceAll("&", "§"));
		}
		
		
		return false;
	}

}
