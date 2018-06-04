package me.guiihsilva.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class comoconseguirchaves implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("comoconseguirchaves")){
			if(!(sender instanceof Player)){
				sender.sendMessage("§f§m---------------------§r §6§lCAIXAS §f§m--------------------");
				sender.sendMessage(" §8- §7Para abrir uma caixa, ou rodar uma roleta, você precisa de sua respectiva chave, existe uma chance de você encontra-la nas seguintes ações:");
				sender.sendMessage("  §7* §f§oVotar com §6/vote §8(100% de chance á cada voto)");
				sender.sendMessage("  §7* §f§oMinerando §8(0.5% de chance por bloco)");
				sender.sendMessage("  §7* §f§oMatando jogadores §8(1% de chance por cabeça)");
				sender.sendMessage("  §7* §f§oEvoluindo de rank §8(100% de chance á cada rank)");	
				sender.sendMessage(" §4- §c§oFuturamente, mais maneiras de conseguir chaves!");
				sender.sendMessage("§f§m---------------------§r §6§lCAIXAS §f§m---------------------");
				return true;
			}
			Player p = (Player) sender;
	    	for (int i = 10;i>0;i--){
		    	sender.sendMessage(" ");
	    	}
			sender.sendMessage("§f§m---------------------§r §6§lCAIXAS §f§m---------------------");
			sender.sendMessage(" §8- §7Para abrir uma caixa, ou rodar uma roleta, você precisa de sua respectiva chave, existe uma chance de você encontra-la nas seguintes ações:");
			sender.sendMessage("  §7* §f§oVotar com §6/vote §8(100% de chance á cada voto)");
			sender.sendMessage("  §7* §f§oMinerando §8(0.5% de chance por bloco)");
			sender.sendMessage("  §7* §f§oMatando jogadores §8(1% de chance por cabeça)");
			sender.sendMessage("  §7* §f§oEvoluindo de rank §8(100% de chance á cada rank)");	
			sender.sendMessage(" §4- §c§oFuturamente, mais maneiras de conseguir chaves!");
			sender.sendMessage("§f§m---------------------§r §6§lCAIXAS §f§m---------------------");
			p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 10, 2);
		}
		return false;
	}
}
