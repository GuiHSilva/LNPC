package me.guiihsilva.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.guiihsilva.Main;
import net.sacredlabyrinth.phaed.simpleclans.ClanPlayer;

public class pvpentreclan implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)){
			sender.sendMessage("§cComando para jogadores in-gamer");
			return true;
		}else{
			Player p = (Player) sender;
			if (args.length == 0){
				p.sendMessage("§2*§a O que é o pvp entre clan? Por padrão, você e os membros do seu clan, não pode se bater, mas é possível mudar isso, com essa função!");
				p.sendMessage("§2*§f /pvpentreclan permitir §7- §aVocê e seus membros vão poder se bater!");
				p.sendMessage("§2*§f /pvpentreclan bloquear §7- §aVocê e seus membros não vão poder se bater!");
				return true;
			}
			if (args[0].equalsIgnoreCase("permitir")){
				if (Main.core != null) {
					ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
					if (cp != null) {
						Main.core.getClanManager().getClanPlayer(p).setFriendlyFire(true);
						sender.sendMessage("§bPvP entra clan definido como: §apermitido");
					}else{
						sender.sendMessage("§cClan não encontrado!");
						return true;
					}
				}
			}else if (args[0].equalsIgnoreCase("bloquear")){
				if (Main.core != null) {
					ClanPlayer cp = Main.core.getClanManager().getClanPlayer(p);
					if (cp != null) {
						Main.core.getClanManager().getClanPlayer(p).setFriendlyFire(false);
						sender.sendMessage("§bPvP entra clan definido como: §cbloqueado");
					}else{
						sender.sendMessage("§cClan não encontrado!");
						return true;
					}
				}
			}else{
				p.sendMessage("§2*§a O que é o pvp entre clan? Por padrão, você e os membros do seu clan, não pode se bater, mas é possível mudar isso, com essa função!");
				p.sendMessage("§2*§f /pvpentreclan permitir §7- §aVocê e seus membros vão poder se bater!");
				p.sendMessage("§2*§f /pvpentreclan bloquear §7- §aVocê e seus membros não vão poder se bater!");
				return true;
			}
		}
		return false;
	}

}
