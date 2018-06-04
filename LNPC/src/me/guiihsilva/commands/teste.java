package me.guiihsilva.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.guiihsilva.utils.FormarNumber;


public class teste implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage("§aTestado com êxito!");
		if (args.length == 1){
			double i = Double.parseDouble(args[0]);
			sender.sendMessage("§aNúmero: §f" + FormarNumber.formatValue(i));
		}
		return false;
	}
}
