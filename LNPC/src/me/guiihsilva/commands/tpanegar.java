package me.guiihsilva.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpanegar implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tpanegar")) {
			if (!(sender instanceof Player)){
				sender.sendMessage("§cO console não pode fazer isso!");
				return true;
			}else{
				if (args.length == 0){
					sender.sendMessage("§cUso correto: /tpanegar [Jogador]");
					return true;
				}else if (args.length > 1){
					sender.sendMessage("§cUso correto: /tpanegar [Jogador]");
					return true;
				}else if (args.length == 1){
					Player requester = Bukkit.getPlayer(args[0]);
					if (checkPlayers(sender, requester)){
						return true;
					}else{
						if (tpa.tparequest.get(requester) == sender){
							tpa.tparequest.remove(requester);
							requester.sendMessage("§6» §eSeu pedido de teleporte foi negado");
							sender.sendMessage("§6» §eO pedido de teleporte foi negado");
						}else{
							sender.sendMessage("§cNenhum pedido de teleporte para negar!");
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkPlayers(CommandSender sender, Player target)
	  {
	    if (target == null)
	    {
	      sender.sendMessage("§cJogador não encontrado! ");
	      return true;
	    }
	    if (sender.getName().equalsIgnoreCase(target.getName()))
	    {
	      sender.sendMessage("§cNão pode ser você mesmo!");
	      return true;
	    }
	    return false;
	  }

}
