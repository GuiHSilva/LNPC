package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class cc implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
	    if ((command.getName().equalsIgnoreCase("limpar")) && (sender.hasPermission("chat.limpar"))){
	    	for (int i = 150;i>0;i--){
		    	Bukkit.broadcastMessage("");
	    	}

	    	Bukkit.broadcastMessage("§c* Chat foi limpo!");
	    	}
		return false;
	}

}
