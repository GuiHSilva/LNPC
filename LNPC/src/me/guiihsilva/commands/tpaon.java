package me.guiihsilva.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.utils.TpaManager;

public class tpaon implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpaon")){
			if(!(sender instanceof Player)){
				sender.sendMessage("O console nao tem tpa para ser alterado");
				return true;
			}else{
				if (sender.hasPermission("tpa.alterar")){
					sender.sendMessage("§e»§6 Você alterou o status do seu tpa para: §2§lON");
					sender.sendMessage("§e»§6 Agora você pode receber pedidos te teleporte!");
					TpaManager.tpastatus.remove(sender);
					TpaManager.tpastatus.put((Player) sender, "on");
				}else{
					sender.sendMessage("§e»§6 Apenas VIPs e YouTubers tem acesso á isto!");
					return true;
				}
			}
		}
		return false;
	}

}
