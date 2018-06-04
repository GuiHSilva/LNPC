package me.guiihsilva.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class lojas implements CommandExecutor,Listener{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("lojas")){
			if (!(sender instanceof Player)){
				sender.sendMessage("§cO comando não é suportado pelo console!");
				return true;
			}else{
				
			}
		}
		return false;
	}

}
