package me.guiihsilva.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class score implements CommandExecutor{
	

	public static HashMap<String, ScoreBoardToggle> score = new HashMap<>();
	public enum ScoreBoardToggle {
	    OFF, ON
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("score")){
			if (!(sender instanceof Player)){
				sender.sendMessage("§cO console não possui um scoreboard para ser desativado!");
				return true;
			}
		}
		if (args.length == 0){
			sender.sendMessage("§cUso correto: /" + cmd.getName() + " [on/off]");
			return true;
		}
		Player p = (Player) sender;
		if (args[0].equalsIgnoreCase("on")){
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fb on " + p.getName());
			return true;
		}
		if (args[0].equalsIgnoreCase("off")){
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "fb off " + p.getName());
			return true;
		}else{
			sender.sendMessage("§cUso correto: /" + cmd.getName() + " [on/off]");
			return true;
		}
	}

}
