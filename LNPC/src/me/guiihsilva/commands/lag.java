package me.guiihsilva.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.guiihsilva.utils.Lag;

public class lag implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.hasPermission("lag.ver")) {
			sender.sendMessage("§cNecessário cargo [SubDono] ou superiror para isso!");
		} else {
			double tps = Lag.getTPS();
			double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);
			sender.sendMessage("§aTPS atual: §e" + tps);
			sender.sendMessage("§aPorcentagem de lag: §e" + lag + "%");
		}
		return false;
	}

}
